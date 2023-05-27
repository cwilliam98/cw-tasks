package com.cwilliam.task.manager.services;

import com.cwilliam.task.manager.entities.Task;
import com.cwilliam.task.manager.entities.TaskDto;
import com.cwilliam.task.manager.entities.User;
import com.cwilliam.task.manager.repositories.TaskRepository;
import com.cwilliam.task.manager.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskManagerService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public Page<TaskDto> findAll(Pageable pageable){
        Page<Task> tasks = taskRepository.findAll(pageable);
        return tasks.map(t -> new TaskDto(t));
    }

    public TaskDto findById(Long taskId){
        TaskDto dto = new TaskDto(taskRepository.findById(taskId).get());
        return dto;
    }

    public TaskDto createTask(TaskDto taskDto){
        Task task = entityToDto(taskDto);
        taskRepository.save(task);
        TaskDto dto = new TaskDto(task);
        return dto;
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    public TaskDto updateTask(Long taskId, TaskDto taskUpdated) {
        Task task = taskRepository.findById(taskId).get();
        task.setDone(taskUpdated.isDone());
        task.setDescription(taskUpdated.getDescription());
        task.setTitle(taskUpdated.getTitle());
        task.setStatus(taskUpdated.getStatus());
        User user = userRepository.findById(taskUpdated.getAssignedUserId()).get();
        task.setAssignedUser(user);
        taskRepository.save(task);
        TaskDto dto = new TaskDto(task);
        return dto;
    }

    private Task entityToDto(TaskDto dto){
        Task task = new Task();
        task.setDone(dto.isDone());
        task.setDescription(dto.getDescription());
        task.setTitle(dto.getTitle());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());
        task.setDeadline(dto.getDeadline());
        User user = userRepository.findById(dto.getAssignedUserId()).get();
        task.setAssignedUser(user);
        return task;
    }
}
