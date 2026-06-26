package com.regysmendes.taskmanger.resource;

import com.regysmendes.taskmanger.entities.Task;
import com.regysmendes.taskmanger.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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



}
