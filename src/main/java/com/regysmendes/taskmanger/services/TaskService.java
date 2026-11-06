package com.regysmendes.taskmanger.services;

import com.regysmendes.taskmanger.dto.TaskInsertDTO;
import com.regysmendes.taskmanger.dto.TaskResponseDTO;
import com.regysmendes.taskmanger.dto.TaskUpdateDTO;
import com.regysmendes.taskmanger.entities.Task;
import com.regysmendes.taskmanger.entities.User;
import com.regysmendes.taskmanger.enums.TaskPriority;
import com.regysmendes.taskmanger.enums.TaskStatus;
import com.regysmendes.taskmanger.repository.TaskRepository;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public List<TaskResponseDTO> findAll() {
       User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       List<Task> list = repository.findByUserId(user.getId());
       List<TaskResponseDTO> result = new ArrayList<>();

       for (Task tasksByUser: list){

           TaskResponseDTO responseDTO = new TaskResponseDTO(tasksByUser.getId(), tasksByUser.getTitle(), tasksByUser.getDescription(), tasksByUser.getPriority(), tasksByUser.getStatus());

           result.add(responseDTO);
       }
       return result;
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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Task task = new Task(null, dto.title(), dto.description(), dto.priority(), dto.status());
        task.setUser(user);
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
        task.setTitle(objUpdate.title());
        task.setPriority(objUpdate.priority());
        task.setDescription(objUpdate.description());
    }

    public Task updateStatus(Long id, TaskStatus newStatus) {
        Task task = findByIdIdentity(id);
        task.setStatus(newStatus);
        return repository.save(task);
    }


}
