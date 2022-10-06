package me.gracenam.todoapi.ticket.entity;

import me.gracenam.todoapi.ticket.enums.TicketStatus;
import me.gracenam.todoapi.ticket.enums.TicketType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class TicketTest {

    @Test
    public void builder() {
        Ticket ticket = Ticket.builder()
                .title("Ticket of Test")
                .contents("This is a Test Content")
                .type(TicketType.HOBBY.name())
                .status(TicketStatus.TODO.name())
                .build();

        assertThat(ticket).isNotNull();
    }

}