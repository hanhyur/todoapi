package me.gracenam.todoapi.ticket.dto;

import me.gracenam.todoapi.ticket.controller.TicketController;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class TicketResource extends EntityModel<TicketDto> {

    public TicketResource(TicketDto dto) {
        super(dto);
        add(linkTo(TicketController.class).slash(dto.getId()).withSelfRel());
    }

}
