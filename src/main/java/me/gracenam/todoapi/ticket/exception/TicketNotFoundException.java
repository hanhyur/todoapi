package me.gracenam.todoapi.ticket.exception;

public class TicketNotFoundException extends RuntimeException {

    public TicketNotFoundException(Long id) {
        super("해당 일정이 존재하지 않습니다.");
    }

}
