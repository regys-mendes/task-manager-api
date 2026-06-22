package com.regysmendes.taskmanger.repository;

import com.regysmendes.taskmanger.entities.Task;
import com.regysmendes.taskmanger.entities.TaskPriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByPriority(TaskPriority priority);
}
