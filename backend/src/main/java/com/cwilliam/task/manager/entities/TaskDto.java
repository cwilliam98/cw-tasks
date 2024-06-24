package com.cwilliam.task.manager.entities;

import com.cwilliam.task.manager.entities.enums.Priority;
import com.cwilliam.task.manager.entities.enums.Status;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {

    private Long id;

    private String title;

    private String description;

    private Status status;

    private Priority priority;

    private LocalDate deadline;

    private Long assignedUserId;

    private boolean done;

    private List<String> taskSuggested = new ArrayList<>();

    public TaskDto(Task entity){
        id = entity.getId();
        title = entity.getTitle();
        description = entity.getDescription();
        status = entity.getStatus();
        priority = entity.getPriority();
        deadline = entity.getDeadline();
        assignedUserId = entity.getAssignedUser().getId();
        done = entity.isDone();
    }
}
