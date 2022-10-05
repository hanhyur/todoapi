package me.gracenam.todoapi.task.dto;

import lombok.*;
import me.gracenam.todoapi.task.enums.TaskStatus;
import me.gracenam.todoapi.task.enums.TaskType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Arrays;

@Getter
@Setter
@Builder
@ToString
public class TaskDto {

    private Long id;

    private String title;

    private String contents;

    private String type;

    private String status;

    public void setStatus(String status) {
        this.status = Arrays.stream(TaskStatus.values()).filter(item
                        -> item.name().equals(status))
                .findAny().get().getValue();
    }

}
