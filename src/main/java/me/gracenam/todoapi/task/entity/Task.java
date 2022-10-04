package me.gracenam.todoapi.task.entity;

import lombok.*;
import me.gracenam.todoapi.task.enums.TaskType;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Task {

    private Long id;

    private String title;

    private String contents;

    private LocalDate registeredDate;

    private TaskType taskType;

    private TaskStatus taskStatus;

}
