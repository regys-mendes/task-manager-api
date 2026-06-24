package com.regysmendes.taskmanger.services;

import com.regysmendes.taskmanger.entities.Task;
import com.regysmendes.taskmanger.entities.TaskPriority;
import com.regysmendes.taskmanger.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> findAll() {
        return repository.findAll();
    }

    public Task findById(Long id) {
        Optional<Task> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RuntimeException("Id not found")) ;
    }

    public List<Task> findByPriority(TaskPriority priority) {
        return repository.findByPriority(priority);
    }

    public Task insert(Task obj){
      return repository.save(obj);
    }

}
