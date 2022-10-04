package me.gracenam.todoapi.task.exception;

import me.gracenam.todoapi.task.entity.Task;

public class TaskEmptyException extends RuntimeException {

    public TaskEmptyException(Long id) {
        super("해당 일정이 존재하지 않습니다.");
    }

}
