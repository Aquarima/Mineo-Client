package com.exalow.mineo.services.impl;

import com.exalow.mineo.models.projects.SimpleProject;
import com.exalow.mineo.repositories.ProjectRepository;
import com.exalow.mineo.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;

    @Autowired
    ProjectServiceImpl(ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public SimpleProject findProjectByName(String name) {
        return repository.getByName(name);
    }

    @Override
    public SimpleProject findProjectById(Long id) {
        return repository.getById(id);
    }

    @Override
    public List<SimpleProject> findProjects() {
        return repository.findAll();
    }
}
