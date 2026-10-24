package com.regysmendes.taskmanger.dto;

import com.regysmendes.taskmanger.enums.TaskPriority;
import com.regysmendes.taskmanger.enums.TaskStatus;

public record TaskInsertDTO(  String title,
                              String description,
                              TaskPriority priority,
                              TaskStatus status
){
}
