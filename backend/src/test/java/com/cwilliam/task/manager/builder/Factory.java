package com.cwilliam.task.manager.builder;

import com.cwilliam.task.manager.entities.Task;
import com.cwilliam.task.manager.entities.User;
import com.cwilliam.task.manager.entities.enums.Priority;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Factory {

    public static User userFactory(){
        return User.builder().id(1L).email("email@email.com").username("email").build();
    }

    public static Task taskFactory(){
        return Task.builder().title("Teste")
                .description("Teste descrição")
                .priority(Priority.LOW)
                .assignedUser(userFactory())
                .done(false)
                .deadline(LocalDate.now())
                .build();
    }

    public static PageImpl<Task> tasksListFactory(){
        return new PageImpl<>(Collections.singletonList(taskFactory()));
    }


}
