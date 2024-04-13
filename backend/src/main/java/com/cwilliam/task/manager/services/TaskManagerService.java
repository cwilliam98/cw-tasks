package com.cwilliam.task.manager.services;

import com.cwilliam.task.manager.entities.Task;
import com.cwilliam.task.manager.entities.TaskDto;
import com.cwilliam.task.manager.entities.User;
import com.cwilliam.task.manager.repositories.TaskRepository;
import com.cwilliam.task.manager.repositories.UserRepository;
import com.cwilliam.task.manager.services.exceptions.DataBaseException;
import com.cwilliam.task.manager.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TaskManagerService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public Page<TaskDto> findAll(Pageable pageable){
        Page<Task> tasks = taskRepository.findAll(pageable);
        return tasks.map(TaskDto::new);
    }

    public TaskDto findById(Long taskId){
        var task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new TaskDto(task);
    }

    public TaskDto createTask(TaskDto taskDto){
        Task task = modelMapper.map(taskDto, Task.class);
        taskRepository.save(task);
        return new TaskDto(task);
    }

    public void deleteTask(Long id){
        try {
            taskRepository.deleteById(id);
        } catch(EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        } catch(DataIntegrityViolationException e) {
            throw new DataBaseException("Integrety violation");
        }
    }

    public TaskDto updateTask(Long taskId, TaskDto taskUpdated) {
        try {
            Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
            task.setDone(taskUpdated.isDone());
            task.setDescription(taskUpdated.getDescription());
            task.setTitle(taskUpdated.getTitle());
            task.setStatus(taskUpdated.getStatus());
            User user = userRepository.findById(taskUpdated.getAssignedUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
            task.setAssignedUser(user);
            taskRepository.save(task);
            var dto = new TaskDto(task);
            return dto;
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found" + taskId);
        }
    }
}
