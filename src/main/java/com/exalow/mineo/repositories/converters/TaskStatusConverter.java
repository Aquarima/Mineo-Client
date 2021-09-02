package com.exalow.mineo.repositories.converters;

import com.exalow.mineo.models.projects.tasks.TaskStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TaskStatusConverter implements AttributeConverter<TaskStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TaskStatus status) {
        return status.getKey();
    }

    @Override
    public TaskStatus convertToEntityAttribute(Integer key) {
        return TaskStatus.fromKey(key);
    }
}
