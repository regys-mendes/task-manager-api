package com.regysmendes.taskmanger.services;

import com.regysmendes.taskmanger.entities.Task;
import com.regysmendes.taskmanger.entities.TaskPriority;
import com.regysmendes.taskmanger.entities.TaskStatus;
import com.regysmendes.taskmanger.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @InjectMocks
    private TaskService service;

    @Mock
    private TaskRepository repository;

    @Test
    void update(){

        Task existingTask = new Task(1L, "Título antigo", "Descrição antiga", TaskPriority.BAIXA, TaskStatus.PENDENTE);
        Task updatedData = new Task(null, "Título novo", "Descrição nova", TaskPriority.ALTA, TaskStatus.PENDENTE);

        when(repository.findById(1L)).thenReturn(Optional.of(existingTask));
        when(repository.save(any())).thenReturn(existingTask);

        Task result = service.update(1L, updatedData);

        assertEquals("Título novo", result.getTitle());
        assertEquals(TaskPriority.ALTA, result.getPriority());
    }
}