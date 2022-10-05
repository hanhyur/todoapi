package me.gracenam.todoapi.task.enums;

public enum TaskStatus {

    TODO("해야 함"),
    DOING("하는 중"),
    DONE("완료");

    private String value;

    TaskStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
