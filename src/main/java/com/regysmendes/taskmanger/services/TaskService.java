package com.regysmendes.taskmanger.services;

import com.regysmendes.taskmanger.dto.TaskInsertDTO;
import com.regysmendes.taskmanger.dto.TaskResponseDTO;
import com.regysmendes.taskmanger.entities.Task;
import com.regysmendes.taskmanger.entities.TaskPriority;
import com.regysmendes.taskmanger.entities.TaskStatus;
import com.regysmendes.taskmanger.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> findAll() {
        return repository.findAll();
    }

    public Task findById(Long id) {
        Optional<Task> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Resource not found. Id " + id));
    }

    public List<Task> findByPriority(TaskPriority priority) {
        return repository.findByPriority(priority);
    }

    public TaskResponseDTO insert(TaskInsertDTO dto) {
        Task task = new Task(null, dto.getTitle(), dto.getDescription(), dto.getPriority(), dto.getStatus());
        task = repository.save(task);
        TaskResponseDTO responseDTO = new TaskResponseDTO(task.getId(), task.getTitle(), task.getDescription(), task.getPriority(), task.getStatus());
        return responseDTO;
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    public Task update(Long id, Task obj) {
        Task task = findById(id);
        taskUpdate(task, obj);
        return repository.save(task);
    }

    public void taskUpdate(Task task, Task objUpdate) {
        task.setTitle(objUpdate.getTitle());
        task.setPriority(objUpdate.getPriority());
        task.setDescription(objUpdate.getDescription());
    }

    public Task updateStatus(Long id, TaskStatus newStatus) {
        Task task = findById(id);
        task.setStatus(newStatus);
        return repository.save(task);
    }


}
