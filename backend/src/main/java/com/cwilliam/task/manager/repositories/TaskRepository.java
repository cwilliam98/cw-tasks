package com.cwilliam.task.manager.repositories;

import com.cwilliam.task.manager.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
