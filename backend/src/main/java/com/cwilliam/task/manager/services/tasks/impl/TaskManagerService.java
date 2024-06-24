package com.cwilliam.task.manager.services.tasks.impl;

import com.cwilliam.task.manager.entities.Task;
import com.cwilliam.task.manager.entities.TaskDto;
import com.cwilliam.task.manager.entities.User;
import com.cwilliam.task.manager.repositories.TaskRepository;
import com.cwilliam.task.manager.repositories.UserRepository;
import com.cwilliam.task.manager.services.exceptions.DataBaseException;
import com.cwilliam.task.manager.services.exceptions.ResourceNotFoundException;
import com.cwilliam.task.manager.services.tasks.TaskManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class TaskManagerService implements TaskManager {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final OpenAiChatClient openAiChatClient;

    public Page<TaskDto> findAll(Pageable pageable) {
        Page<Task> tasks = taskRepository.findAll(pageable);
        return tasks.map(TaskDto::new);
    }

    public TaskDto findById(Long taskId) {
        var task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new TaskDto(task);
    }

    public TaskDto createTask(TaskDto taskDto) {
        Task task = modelMapper.map(taskDto, Task.class);
        taskRepository.save(task);
        var promptTemplate = new PromptTemplate("""
                sugira três ou quatro tarefas para concluir a tarefa {taskTitle},
                considere a seguinte descrição da tarefa para realizar as sugestões {taskDescription}
                Retorne somente as tarefas, sem nenhum texto adicional, separadas por vírgula e sem espaços.
                """);
        promptTemplate.add("taskTitle", task.getTitle());
        promptTemplate.add("taskDescription", task.getDescription());
        var promptReturn = openAiChatClient.call(promptTemplate.create()).getResult().getOutput().getContent();
        var taskDtoCreated = new TaskDto(task);
        var promptReturnCleaned = Arrays.stream(promptReturn.trim().split(",")).map(String::trim).toList();
        taskDtoCreated.getTaskSuggested().addAll(promptReturnCleaned);
        return taskDtoCreated;
    }

    public void deleteTask(Long id) {
        try {
            taskRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        } catch (DataIntegrityViolationException e) {
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
            return new TaskDto(task);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found" + taskId);
        }
    }
}
