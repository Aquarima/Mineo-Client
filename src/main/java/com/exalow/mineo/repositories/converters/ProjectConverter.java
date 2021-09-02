package com.exalow.mineo.repositories.converters;

import com.exalow.mineo.models.projects.SimpleProject;
import com.exalow.mineo.repositories.ProjectRepository;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ProjectConverter implements AttributeConverter<SimpleProject, Long> {

    private final ProjectRepository repository;

    ProjectConverter(ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long convertToDatabaseColumn(SimpleProject project) {
        return project.getId();
    }

    @Override
    public SimpleProject convertToEntityAttribute(Long id) {
        return repository.getById(id);
    }
}
