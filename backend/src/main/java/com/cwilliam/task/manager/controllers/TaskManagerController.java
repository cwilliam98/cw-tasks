package com.cwilliam.task.manager.controllers;

import com.cwilliam.task.manager.entities.TaskDto;
import com.cwilliam.task.manager.services.TaskManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Task Manager", description = "Task Manager API")
@RestController
@RequiredArgsConstructor
public class TaskManagerController {

    private final TaskManagerService service;

    @Operation(
            summary = "Fetch all tasks",
            description = "fetches all tasks entities and their data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping
    private ResponseEntity<Page<TaskDto>> getAllTasks(Pageable pageable){
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    @Operation(
            summary = "Fetch a tasks by Id",
            description = "fetches a task entities and their data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/{id}")
    private ResponseEntity<TaskDto> getTaskById(@PathVariable String id){
        int taskId = Integer.parseInt(id);
        TaskDto dto = service.findById((long) taskId);
        return ResponseEntity.ok().body(dto);
    }
    @Operation(
            summary = "Insert a task",
            description = "Insert a task in data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping
    private ResponseEntity<TaskDto> createTask(@RequestBody TaskDto task){
        return ResponseEntity.ok().body(service.createTask(task));
    }
    @Operation(
            summary = "Edit a task by ID",
            description = "Edit a task in data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PutMapping("/{id}")
    private ResponseEntity<TaskDto> updateTask(@PathVariable String id, @RequestBody TaskDto task){
        int taskId = Integer.parseInt(id);

        return ResponseEntity.ok().body(service.updateTask((long) taskId, task));
    }

    @Operation(
            summary = "Delete a tasks by Id",
            description = "Delete a task from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteTask(@PathVariable String id){
        int taskId = Integer.parseInt(id);

        service.deleteTask((long) taskId);
        return ResponseEntity.noContent().build();
    }
}
