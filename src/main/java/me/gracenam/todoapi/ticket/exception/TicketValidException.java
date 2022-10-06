package me.gracenam.todoapi.ticket.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TicketValidException extends RuntimeException {

    public TicketValidException(BindingResult result) {
        super(messageParse(result));
    }

    public static String messageParse(BindingResult result){

        ObjectMapper objectMapper = new ObjectMapper();
        Stream<String> stringStream = result.getFieldErrors().stream().map(item -> item.getDefaultMessage());

        try {
            return objectMapper.writeValueAsString(stringStream.collect(Collectors.toList()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}