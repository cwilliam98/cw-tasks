package com.cwilliam.task.manager.entities;

import com.cwilliam.task.manager.entities.enums.Priority;
import com.cwilliam.task.manager.entities.enums.Status;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {

    private String title;

    private String description;

    private Status status;

    private Priority priority;

    private LocalDate deadline;

    private Long assignedUserId;

    private boolean done;

    public TaskDto(Task entity){
        title = entity.getTitle();
        description = entity.getDescription();
        status = entity.getStatus();
        priority = entity.getPriority();
        deadline = entity.getDeadline();
        assignedUserId = entity.getAssignedUser().getId();
        done = entity.isDone();
    }
}
