package me.gracenam.todoapi.ticket.enums;

public enum TicketStatus {

    TODO("해야 함"),
    DOING("하는 중"),
    DONE("완료");

    private String value;

    TicketStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
