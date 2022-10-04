package me.gracenam.todoapi.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.gracenam.todoapi.task.entity.TaskStatus;
import me.gracenam.todoapi.task.enums.TaskType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskUpdateDto {

    private Long id;

    private String title;

    private String contents;

    private TaskType taskType;

    private TaskStatus taskStatus;

}
