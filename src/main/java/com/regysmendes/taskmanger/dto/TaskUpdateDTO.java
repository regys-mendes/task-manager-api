package com.regysmendes.taskmanger.dto;

import com.regysmendes.taskmanger.entities.TaskPriority;

public class TaskUpdateDTO {

    private String title;
    private String description;
    private TaskPriority priority;

    public TaskUpdateDTO(){
    }

    public TaskUpdateDTO(String title, String description, TaskPriority priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }
}
