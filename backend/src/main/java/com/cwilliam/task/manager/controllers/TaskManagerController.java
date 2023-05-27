package com.cwilliam.task.manager.controllers;

import com.cwilliam.task.manager.entities.Task;
import com.cwilliam.task.manager.entities.TaskDto;
import com.cwilliam.task.manager.services.TaskManagerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/taskManager")
@AllArgsConstructor
public class TaskManagerController {

    private final TaskManagerService service;

    @GetMapping
    private ResponseEntity<Page<TaskDto>> getAllTasks(Pageable pageable){
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    private ResponseEntity<TaskDto> getTaskById(@PathVariable String id){
        Integer taskId = Integer.valueOf(id);
        TaskDto dto = service.findById(taskId.longValue());
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    private ResponseEntity<TaskDto> createTask(@RequestBody TaskDto task){
        return ResponseEntity.ok().body(service.createTask(task));
    }

    @PutMapping("/{id}")
    private ResponseEntity<TaskDto> updateTask(@PathVariable String id, @RequestBody TaskDto task){
        Integer taskId = Integer.valueOf(id);

        return ResponseEntity.ok().body(service.updateTask(taskId.longValue(), task));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteTask(@PathVariable String id){
        Integer taskId = Integer.valueOf(id);

        service.deleteTask(taskId.longValue());
        return ResponseEntity.noContent().build();
    }
}
