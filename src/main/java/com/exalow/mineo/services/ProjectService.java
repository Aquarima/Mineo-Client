package com.exalow.mineo.services;

import com.exalow.mineo.models.projects.SimpleProject;

import java.util.List;

public interface ProjectService {

    SimpleProject findProjectByName(String name);

    SimpleProject findProjectById(Long id);

    List<SimpleProject> findProjects();
}
