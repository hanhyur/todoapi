package me.gracenam.todoapi.ticket.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
public class TicketUpdateDto {

    private String objective;

    private String value;

}
