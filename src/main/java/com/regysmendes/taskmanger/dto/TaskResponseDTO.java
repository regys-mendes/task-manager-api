package com.regysmendes.taskmanger.dto;

import com.regysmendes.taskmanger.enums.TaskPriority;
import com.regysmendes.taskmanger.enums.TaskStatus;

public record TaskResponseDTO( Long id,
                               String title,
                               String description,
                               TaskPriority priority,
                               TaskStatus status) {
}
