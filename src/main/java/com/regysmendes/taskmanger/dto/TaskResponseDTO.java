package com.regysmendes.taskmanger.dto;

import com.regysmendes.taskmanger.entities.TaskPriority;
import com.regysmendes.taskmanger.entities.TaskStatus;

import java.util.Objects;

public class TaskResponseDTO {

    private Long id;
    private String title;
    private String description;
    private TaskPriority priority;
    private TaskStatus status;

    public TaskResponseDTO(){
    }

    public TaskResponseDTO(Long id, String title, String description, TaskPriority priority, TaskStatus status) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TaskResponseDTO that = (TaskResponseDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
