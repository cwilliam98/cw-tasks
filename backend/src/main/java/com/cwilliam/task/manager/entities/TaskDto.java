package com.cwilliam.task.manager.entities;

import com.cwilliam.task.manager.entities.enums.Priority;
import com.cwilliam.task.manager.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
