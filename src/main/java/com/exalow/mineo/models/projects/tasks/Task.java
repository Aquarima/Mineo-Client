package com.exalow.mineo.models.projects.tasks;

import com.exalow.mineo.models.projects.Project;

import java.sql.Timestamp;

public interface Task {

    Long getId();

    Project getProject();

    String getTitle();

    void setTitle(String title);

    TaskStatus getStatus();

    void setStatus(TaskStatus status);

    TaskPriority getPriority();

    void setPriority(TaskPriority priority);

    Timestamp getStartTime();

    void setStartTime(Timestamp startTime);

    Timestamp getEndTime();

    void setEndTime(Timestamp endTime);

    boolean isExpired();

    String getContent();

    void setContent(String content);

    Timestamp getCreationTime();
}
