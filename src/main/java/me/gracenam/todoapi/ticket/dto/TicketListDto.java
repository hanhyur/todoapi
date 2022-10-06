package me.gracenam.todoapi.ticket.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.gracenam.todoapi.ticket.enums.TicketStatus;

import java.time.LocalDate;
import java.util.Arrays;

@Getter
@Setter
@ToString
public class TicketListDto {

    private Long id;

    private String title;

    private String type;

    private String status;

    private LocalDate registeredDate;

    public void setStatus(String status) {
        this.status = Arrays.stream(TicketStatus.values()).filter(item
                        -> item.name().equals(status))
                .findAny().get().getValue();
    }

}
