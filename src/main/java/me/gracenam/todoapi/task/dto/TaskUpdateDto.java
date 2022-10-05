package me.gracenam.todoapi.task.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
public class TaskUpdateDto {

    private String objective;

    private String value;

}
