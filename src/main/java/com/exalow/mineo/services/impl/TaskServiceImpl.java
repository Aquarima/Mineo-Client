package com.exalow.mineo.services.impl;

import com.exalow.mineo.models.projects.Project;
import com.exalow.mineo.models.projects.tasks.SimpleTask;
import com.exalow.mineo.repositories.TaskRepository;
import com.exalow.mineo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    @Autowired
    TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public SimpleTask findTasksByTitle(String title) {
        return repository.getByTitle(title);
    }

    @Override
    public SimpleTask findTasksByProject(Project project) {
        return null;
    }

    @Override
    public SimpleTask findTaskById(Long id) {
        return repository.getById(id);
    }

    @Override
    public List<SimpleTask> findTasks() {
        return repository.findAll();
    }
}
