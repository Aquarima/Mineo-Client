package com.exalow.mineo.common;

import com.exalow.mineo.models.projects.tasks.Task;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public interface TaskCalendar {

    enum ViewType {
        MONTH, WEEK, DAY
    }

    void setViewType(ViewType viewType);

    void setTasks(List<Task> tasks);

    Map<Calendar, List<Task>> next(int scope);

    Map<Calendar, List<Task>> previous(int scope);
}
