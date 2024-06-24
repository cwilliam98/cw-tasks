package com.cwilliam.task.manager.services.tasks;

import com.cwilliam.task.manager.entities.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskManager {
    Page<TaskDto> findAll(Pageable pageable);
    TaskDto findById(Long taskId);
    TaskDto createTask(TaskDto taskDto);
    void deleteTask(Long id);
    TaskDto updateTask(Long taskId, TaskDto taskUpdated);
}
