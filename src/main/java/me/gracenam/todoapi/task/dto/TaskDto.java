package me.gracenam.todoapi.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.gracenam.todoapi.task.entity.TaskStatus;
import me.gracenam.todoapi.task.enums.TaskType;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private String title;

    private String contents;

    private TaskType taskType;

    private TaskStatus taskStatus;

}
