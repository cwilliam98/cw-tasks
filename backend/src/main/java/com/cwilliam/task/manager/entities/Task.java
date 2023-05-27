package com.cwilliam.task.manager.entities;

import com.cwilliam.task.manager.entities.enums.Priority;
import com.cwilliam.task.manager.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = "assigned_user_id")
    private User assignedUser;

    private LocalDateTime createdAt = LocalDateTime.now();

    private boolean done;
}
