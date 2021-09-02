package com.exalow.mineo.models.projects.tasks;

import com.exalow.mineo.models.projects.Project;
import com.exalow.mineo.models.projects.SimpleProject;
import com.exalow.mineo.repositories.converters.ProjectConverter;
import com.exalow.mineo.repositories.converters.TaskPriorityConverter;
import com.exalow.mineo.repositories.converters.TaskStatusConverter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "Tasks")
public class SimpleTask implements Task {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @Convert(converter = ProjectConverter.class)
    @JoinColumn(name = "project_id")
    private SimpleProject project;

    @Column(name = "title", nullable = false)
    private String title;

    @Convert(converter = TaskStatusConverter.class)
    @Column(name = "status", nullable = false)
    private TaskStatus status;

    @Convert(converter = TaskPriorityConverter.class)
    @Column(name = "priority", nullable = false)
    private TaskPriority priority;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @Column(name = "content", nullable = false)
    private String content;

    @CreationTimestamp
    @Column(name = "creation_time", nullable = false, updatable = false)
    private Timestamp creationTime;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Project getProject() {
        return project;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public TaskStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public TaskPriority getPriority() {
        return priority;
    }

    @Override
    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    @Override
    public Timestamp getStartTime() {
        return startTime;
    }

    @Override
    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Override
    public Timestamp getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean isExpired() {

        if (endTime == null)
            return false;

        return endTime.before(Timestamp.from(Instant.now()));
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public Timestamp getCreationTime() {
        return creationTime;
    }
}
