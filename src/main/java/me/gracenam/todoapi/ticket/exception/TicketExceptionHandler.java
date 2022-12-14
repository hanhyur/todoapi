package me.gracenam.todoapi.ticket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TicketExceptionHandler {

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity notFoundErrorHandler(RuntimeException e){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(TicketValidException.class)
    public ResponseEntity badRequestErrorHandler(RuntimeException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}

