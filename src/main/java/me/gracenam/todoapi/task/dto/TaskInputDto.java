package me.gracenam.todoapi.task.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Builder
public class TaskInputDto {

    @NotBlank(message = "제목을 입력하세요")
    private String title;

    private String contents;

    @NotNull
    private String type;

}
