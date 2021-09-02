package com.exalow.mineo.repositories;

import com.exalow.mineo.models.projects.SimpleProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<SimpleProject, Long> {

    @Query("SELECT project FROM SimpleProject project WHERE project.name LIKE ?1")
    SimpleProject getByName(String name);
}
