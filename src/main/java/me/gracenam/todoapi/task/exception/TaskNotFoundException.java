package me.gracenam.todoapi.task.exception;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(Long id) {
        super("해당 일정이 존재하지 않습니다.");
    }

}
