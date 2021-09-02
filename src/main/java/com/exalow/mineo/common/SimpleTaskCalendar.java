package com.exalow.mineo.common;

import com.exalow.mineo.models.projects.tasks.Task;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleTaskCalendar implements TaskCalendar {

    private final Calendar calendar;
    private ViewType viewType;
    private List<Task> tasks;

    public SimpleTaskCalendar(ViewType viewType) {
        this.calendar = Calendar.getInstance();
        this.viewType = viewType;
        this.tasks = new ArrayList<>();
    }

    public SimpleTaskCalendar() {
        this(ViewType.WEEK);
    }

    @Override
    public void setViewType(ViewType viewType) {
        this.viewType = viewType;
    }

    @Override
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public Map<Calendar, List<Task>> next(int capture) {
        return select(1, capture);
    }

    @Override
    public Map<Calendar, List<Task>> previous(int capture) {
        return select(-1, capture);
    }

    private List<Task> findTasks(int field) {
        return tasks.stream()
                .filter(task -> {
                    Calendar taskCalendar = Calendar.getInstance();
                    taskCalendar.setTime(task.getCreationTime());
                    return taskCalendar.get(field) == calendar.get(field);
                }).collect(Collectors.toList());
    }

    private Map<Calendar, List<Task>> select(int iteration, int capture) {

        Map<Calendar, List<Task>> data = new HashMap<>();

        if (viewType == ViewType.DAY) {
            for (int i = -1; i < capture; i++) {
                calendar.add(Calendar.HOUR_OF_DAY, iteration);
                data.put(calendar, findTasks(Calendar.HOUR_OF_DAY));
            }
        }

        if (viewType == ViewType.WEEK) {
            for (int i = -1; i < capture; i++) {
                calendar.add(Calendar.DAY_OF_WEEK, iteration);
                data.put(calendar, findTasks(Calendar.DAY_OF_WEEK));
            }
        }

        if (viewType == ViewType.MONTH) {
            for (int i = -1; i < capture; i++) {
                calendar.add(Calendar.DAY_OF_WEEK, iteration);
                data.put(calendar, findTasks(Calendar.DAY_OF_WEEK));
            }
        }

        return data;
    }
}
