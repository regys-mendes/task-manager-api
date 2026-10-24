package com.regysmendes.taskmanger.dto;

import com.regysmendes.taskmanger.enums.TaskPriority;

public record TaskUpdateDTO(String title,
                            String description,
                            TaskPriority priority) {
}
