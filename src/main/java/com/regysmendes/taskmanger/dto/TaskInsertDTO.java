package com.regysmendes.taskmanger.dto;

import com.regysmendes.taskmanger.entities.TaskPriority;
import com.regysmendes.taskmanger.entities.TaskStatus;

import java.io.Serializable;

public class TaskInsertDTO implements Serializable {

    private String title;
    private String description;
    private TaskPriority priority;
    private TaskStatus status;

    public TaskInsertDTO(){
    }

    public TaskInsertDTO(String title, String description, TaskPriority priority, TaskStatus status) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
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

}
