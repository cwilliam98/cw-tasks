package com.cwilliam.task.manager.controllers;

import com.cwilliam.task.manager.entities.TaskDto;
import com.cwilliam.task.manager.services.TaskManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/taskManager")
@RequiredArgsConstructor
public class TaskManagerController {

    private final TaskManagerService service;

    @GetMapping
    private ResponseEntity<Page<TaskDto>> getAllTasks(Pageable pageable){
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    private ResponseEntity<TaskDto> getTaskById(@PathVariable String id){
        int taskId = Integer.parseInt(id);
        TaskDto dto = service.findById((long) taskId);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    private ResponseEntity<TaskDto> createTask(@RequestBody TaskDto task){
        return ResponseEntity.ok().body(service.createTask(task));
    }

    @PutMapping("/{id}")
    private ResponseEntity<TaskDto> updateTask(@PathVariable String id, @RequestBody TaskDto task){
        int taskId = Integer.parseInt(id);

        return ResponseEntity.ok().body(service.updateTask((long) taskId, task));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteTask(@PathVariable String id){
        int taskId = Integer.parseInt(id);

        service.deleteTask((long) taskId);
        return ResponseEntity.noContent().build();
    }
}
