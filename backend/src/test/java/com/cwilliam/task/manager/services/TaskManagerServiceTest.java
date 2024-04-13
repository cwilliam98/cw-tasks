package com.cwilliam.task.manager.services;

import com.cwilliam.task.manager.builder.Factory;
import com.cwilliam.task.manager.entities.Task;
import com.cwilliam.task.manager.entities.TaskDto;
import com.cwilliam.task.manager.entities.User;
import com.cwilliam.task.manager.entities.enums.Priority;
import com.cwilliam.task.manager.repositories.TaskRepository;
import com.cwilliam.task.manager.repositories.UserRepository;
import com.cwilliam.task.manager.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class TaskManagerServiceTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TaskManagerService service;


    @Test
    void shouldReturnATaskWhenIdIsValid() {
        long taskId = 1L;
        var taskMock = Factory.taskFactory();

        Mockito.when(taskRepository.findById(taskId)).thenReturn(Optional.of(taskMock));

        var task = service.findById(taskId);
        Assertions.assertNotNull(task);
        Mockito.verify(taskRepository, Mockito.times(1)).findById(taskId);
    }

    @Test
    void shouldReturnAListOfTask() {
        var taskMock = Factory.tasksListFactory();
        Pageable pageable = PageRequest.of(0, 10);

        Mockito.when(taskRepository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(taskMock);

        var tasks = service.findAll(pageable);

        Assertions.assertNotNull(tasks);
        Mockito.verify(taskRepository, Mockito.times(1)).findAll(pageable);
    }

    @Test
    void shouldDeleteTaskWhenIdExists(){
        long taskId = 1L;

        Mockito.doNothing().when(taskRepository).deleteById(taskId);

        Assertions.assertDoesNotThrow(()-> {
            service.deleteTask(taskId);
        });

        Mockito.verify(taskRepository, Mockito.times(1)).deleteById(taskId);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenIdNotFound(){
        long taskIdNotFound = 100000L;

        Mockito.doThrow(EmptyResultDataAccessException.class).when(taskRepository).deleteById(taskIdNotFound);

        Assertions.assertThrows(ResourceNotFoundException.class, ()-> {
            service.deleteTask(taskIdNotFound);
        });

    }

}
