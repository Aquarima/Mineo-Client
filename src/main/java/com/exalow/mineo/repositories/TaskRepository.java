package com.exalow.mineo.repositories;

import com.exalow.mineo.models.projects.tasks.SimpleTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<SimpleTask, Long> {

    @Query("SELECT task FROM SimpleTask task WHERE task.title LIKE ?1")
    SimpleTask getByTitle(String title);

    @Query("SELECT t FROM SimpleTask t WHERE t.project.id = ?1")
    List<SimpleTask> getByProjectId(Long projectId);
}
