package com.regysmendes.taskmanger.resource;

import com.regysmendes.taskmanger.entities.Task;
import com.regysmendes.taskmanger.entities.TaskPriority;
import com.regysmendes.taskmanger.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TaskResource {

    private final TaskService service;

    public TaskResource(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Task>> findAll() {
        List<Task> obj = service.findAll();
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id){
        Task obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/priority/{priority}")
    public ResponseEntity<List <Task>> findByPriority(@PathVariable TaskPriority priority){
        List<Task> obj = service.findByPriority(priority);
        return ResponseEntity.ok().body(obj);
    }


}
