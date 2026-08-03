package com.regysmendes.taskmanger.resource;

import com.regysmendes.taskmanger.dto.TaskInsertDTO;
import com.regysmendes.taskmanger.dto.TaskResponseDTO;
import com.regysmendes.taskmanger.dto.TaskUpdateDTO;
import com.regysmendes.taskmanger.entities.Task;
import com.regysmendes.taskmanger.entities.TaskPriority;
import com.regysmendes.taskmanger.entities.TaskStatus;
import com.regysmendes.taskmanger.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TaskResource {

    private final TaskService service;

    public TaskResource(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> findAll() {
        List<TaskResponseDTO> obj = service.findAll();
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskResponseDTO> findById(@PathVariable Long id){
        TaskResponseDTO obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/priority/{priority}")
    public ResponseEntity<List <TaskResponseDTO>> findByPriority(@PathVariable TaskPriority priority){
        List<TaskResponseDTO> obj = service.findByPriority(priority);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> insert(@RequestBody TaskInsertDTO obj){
        TaskResponseDTO newObj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newObj.getId())
                .toUri();
        return ResponseEntity.created(uri).body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TaskResponseDTO> update(@PathVariable Long id, @RequestBody TaskUpdateDTO dto){
        TaskResponseDTO task = service.update(id, dto);
        return ResponseEntity.ok().body(task);
    }

    @PatchMapping(value = "/{id}/status")
    public ResponseEntity<Task> status(@PathVariable Long id, @RequestBody TaskStatus status){
        Task task = service.updateStatus(id, status);
        return ResponseEntity.ok().body(task);
    }

}
