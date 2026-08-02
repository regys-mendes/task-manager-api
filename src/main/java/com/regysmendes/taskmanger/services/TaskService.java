package com.regysmendes.taskmanger.services;

import com.regysmendes.taskmanger.dto.TaskInsertDTO;
import com.regysmendes.taskmanger.dto.TaskResponseDTO;
import com.regysmendes.taskmanger.dto.TaskUpdateDTO;
import com.regysmendes.taskmanger.entities.Task;
import com.regysmendes.taskmanger.entities.TaskPriority;
import com.regysmendes.taskmanger.entities.TaskStatus;
import com.regysmendes.taskmanger.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Task findByIdIdentity(Long id) {
        Optional<Task> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Resource not found. Id " + id));
    }

    public TaskResponseDTO findById(Long id){
        Task task = findByIdIdentity(id);
        return new TaskResponseDTO(task.getId(), task.getTitle(), task.getDescription(), task.getPriority(), task.getStatus());
    }

    public List<TaskResponseDTO> findByPriority(TaskPriority priority) {
        List<Task> list = repository.findByPriority(priority);
        List<TaskResponseDTO> result = new ArrayList<>();

        for (Task task: list){
            TaskResponseDTO responseDTO = new TaskResponseDTO(task.getId(), task.getTitle(), task.getDescription(), task.getPriority(), task.getStatus());
            result.add(responseDTO);
        }
        return result;
    }

    public TaskResponseDTO insert(TaskInsertDTO dto) {
        Task task = new Task(null, dto.getTitle(), dto.getDescription(), dto.getPriority(), dto.getStatus());
        task = repository.save(task);
        TaskResponseDTO responseDTO = new TaskResponseDTO(task.getId(), task.getTitle(), task.getDescription(), task.getPriority(), task.getStatus());
        return responseDTO;
    }

    public void delete(Long id) {
        findByIdIdentity(id);
        repository.deleteById(id);
    }

    public TaskResponseDTO update(Long id, TaskUpdateDTO dto) {
        Task task = findByIdIdentity(id);
        taskUpdate(task, dto);
        repository.save(task);
        TaskResponseDTO responseDTO = new TaskResponseDTO(task.getId(), task.getTitle(), task.getDescription(), task.getPriority(), task.getStatus());
        return  responseDTO;
    }

    public void taskUpdate(Task task, TaskUpdateDTO objUpdate) {
        task.setTitle(objUpdate.getTitle());
        task.setPriority(objUpdate.getPriority());
        task.setDescription(objUpdate.getDescription());
    }

    public Task updateStatus(Long id, TaskStatus newStatus) {
        Task task = findByIdIdentity(id);
        task.setStatus(newStatus);
        return repository.save(task);
    }


}
