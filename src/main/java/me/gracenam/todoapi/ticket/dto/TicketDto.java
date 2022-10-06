package me.gracenam.todoapi.ticket.dto;

import lombok.*;
import me.gracenam.todoapi.ticket.enums.TicketStatus;

import java.util.Arrays;

@Getter
@Setter
@Builder
@ToString
public class TicketDto {

    private Long id;

    private String title;

    private String contents;

    private String type;

    private String status;

    public void setStatus(String status) {
        this.status = Arrays.stream(TicketStatus.values()).filter(item
                        -> item.name().equals(status))
                .findAny().get().getValue();
    }

}
