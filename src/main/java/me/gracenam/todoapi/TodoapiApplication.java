package me.gracenam.todoapi;

import me.gracenam.todoapi.task.dto.TaskDto;
import me.gracenam.todoapi.task.entity.Task;
import me.gracenam.todoapi.task.entity.TaskStatus;
import me.gracenam.todoapi.task.enums.TaskType;
import me.gracenam.todoapi.task.service.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class TodoapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoapiApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(TaskService taskService) throws Exception {
        return (String[] args) -> {
            TaskDto taskDto = TaskDto.builder()
                    .title("Sample")
                    .contents("Sample Contents")
                    .taskType(TaskType.LIFE)
                    .taskStatus(TaskStatus.TO_DO)
                    .build();

            taskService.save(taskDto);
        };
    }

}
