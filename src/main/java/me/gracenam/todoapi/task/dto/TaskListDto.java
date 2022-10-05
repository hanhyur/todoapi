package me.gracenam.todoapi.task.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.gracenam.todoapi.task.enums.TaskStatus;

import java.time.LocalDate;
import java.util.Arrays;

@Getter
@Setter
@ToString
public class TaskListDto {

    private Long id;

    private String title;

    private String type;

    private String status;

    private LocalDate registeredDate;

    public void setStatus(String status) {
        this.status = Arrays.stream(TaskStatus.values()).filter(item
                        -> item.name().equals(status))
                .findAny().get().getValue();
    }

}
