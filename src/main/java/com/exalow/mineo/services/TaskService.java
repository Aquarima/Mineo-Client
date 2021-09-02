package com.exalow.mineo.services;

import com.exalow.mineo.models.projects.Project;
import com.exalow.mineo.models.projects.tasks.SimpleTask;

import java.util.List;

public interface TaskService {

    SimpleTask findTasksByTitle(String title);

    SimpleTask findTasksByProject(Project project);

    SimpleTask findTaskById(Long id);

    List<SimpleTask> findTasks();
}
