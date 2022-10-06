package me.gracenam.todoapi.ticket.controller;

import lombok.RequiredArgsConstructor;
import me.gracenam.todoapi.ticket.dto.*;
import me.gracenam.todoapi.ticket.exception.TicketValidException;
import me.gracenam.todoapi.ticket.service.TicketService;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity findTicketList(TicketSearchDto dto) {
        List<TicketListDto> ticketList = ticketService.findTicketList(dto);

        List<TicketResource> resourceList = new ArrayList<>();

        for (TicketListDto listDto : ticketList) {
            TicketDto ticketDto = ticketService.findTicket(listDto.getId());

            TicketResource ticketResource = new TicketResource(ticketDto);
            resourceList.add(ticketResource);
        }

        return ResponseEntity.ok(resourceList);
    }

    @GetMapping("/{id}")
    public ResponseEntity findTicket(@PathVariable Long id) {
        TicketDto ticketDto = ticketService.findTicket(id);

        TicketResource ticketResource = new TicketResource(ticketDto);
        ticketResource.add(linkTo(TicketController.class).withRel("ticketList"));
        ticketResource.add(linkTo(TicketController.class).slash(ticketDto.getId()).withRel("updateTicket"));

        return ResponseEntity.ok(ticketResource);
    }

    @PostMapping
    public ResponseEntity createTicket(@RequestBody @Valid TicketInputDto dto,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new TicketValidException(bindingResult);
        }

        Long id = ticketService.save(dto);
        TicketDto newTicket = ticketService.findTicket(id);

        WebMvcLinkBuilder selfLinkBuilder = linkTo(TicketController.class).slash(newTicket.getId());
        URI createdURI = selfLinkBuilder.toUri();
        TicketResource ticketResource = new TicketResource(newTicket);
        ticketResource.add(linkTo(TicketController.class).withRel("ticketList"));
        ticketResource.add(selfLinkBuilder.withRel("updateTicket"));

        return ResponseEntity.created(createdURI).body(ticketResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTicket(@PathVariable Long id,
                                       @RequestBody TicketInputDto dto) {
        ticketService.update(id, dto);
        TicketDto ticketDto = ticketService.findTicket(id);

        TicketResource ticketResource = new TicketResource(ticketDto);
        ticketResource.add(linkTo(TicketController.class).withRel("ticketList"));

        return ResponseEntity.status(HttpStatus.FOUND).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateObjective(@PathVariable Long id,
                                          @RequestBody TicketUpdateDto dto) {
        ticketService.updateByObjective(id, dto);

        return ResponseEntity.status(HttpStatus.FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTicket(@PathVariable Long id) {
        ticketService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
