package com.exalow.mineo.repositories.converters;

import com.exalow.mineo.models.projects.tasks.TaskPriority;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TaskPriorityConverter implements AttributeConverter<TaskPriority, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TaskPriority priority) {
        return priority.getKey();
    }

    @Override
    public TaskPriority convertToEntityAttribute(Integer key) {
        return TaskPriority.fromKey(key);
    }
}
