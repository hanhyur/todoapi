package me.gracenam.todoapi.ticket.controller;

import lombok.RequiredArgsConstructor;
import me.gracenam.todoapi.ticket.dto.*;
import me.gracenam.todoapi.ticket.exception.TicketValidException;
import me.gracenam.todoapi.ticket.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity findTicketList(TicketSearchDto dto) {
        List<TicketListDto> ticketList = ticketService.findTicketList(dto);

        return ResponseEntity.ok().body(ticketList);
    }

    @GetMapping("/{id}")
    public ResponseEntity findTicket(@PathVariable Long id) {
        TicketDto ticketDto = ticketService.findTicket(id);

        return ResponseEntity.ok().body(ticketDto);
    }

    @PostMapping
    public ResponseEntity createTicket(@RequestBody @Valid TicketInputDto dto,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new TicketValidException(bindingResult);
        }

        Long id = ticketService.save(dto);

        return ResponseEntity.ok().body(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTicket(@PathVariable Long id,
                                       @RequestBody TicketInputDto dto) {

        ticketService.update(id, dto);

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

        return ResponseEntity.ok().build();
    }
}
