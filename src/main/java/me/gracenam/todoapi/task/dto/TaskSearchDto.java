package me.gracenam.todoapi.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskSearchDto {

    private Long id;

    private String contents;

}
