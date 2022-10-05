package me.gracenam.todoapi.task.entity;

import me.gracenam.todoapi.task.enums.TaskStatus;
import me.gracenam.todoapi.task.enums.TaskType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class TaskTest {

    @Test
    public void builder() {
        Task task = Task.builder()
                .title("Task of Test")
                .contents("This is a Test Content")
                .type(TaskType.HOBBY.name())
                .status(TaskStatus.TODO.name())
                .build();

        assertThat(task).isNotNull();
    }

}