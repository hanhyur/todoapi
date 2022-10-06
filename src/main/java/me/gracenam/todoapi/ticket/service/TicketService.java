package me.gracenam.todoapi.ticket.service;

import lombok.RequiredArgsConstructor;
import me.gracenam.todoapi.ticket.dto.*;
import me.gracenam.todoapi.ticket.entity.Ticket;
import me.gracenam.todoapi.ticket.enums.TicketStatus;
import me.gracenam.todoapi.ticket.exception.TicketNotFoundException;
import me.gracenam.todoapi.ticket.mapper.TicketMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketMapper ticketMapper;

    private final ModelMapper modelMapper;

    public List<TicketListDto> findTicketList(TicketSearchDto dto) {
        List<TicketListDto> ticketListDtos = ticketMapper.findTicketList(dto);

        return ticketListDtos;
    }

    public TicketDto findTicket(Long id) {
        Optional<TicketDto> dto = ticketMapper.findById(id);

        if (!dto.isPresent()) {
            throw new TicketNotFoundException(id);
        }

        return dto.get();
    }

    @Transactional
    public Long save(TicketInputDto dto) {
        Ticket ticket = modelMapper.map(dto, Ticket.class);
        ticket.setStatus(TicketStatus.TODO.name());

        ticketMapper.insertTicket(ticket);

        return ticket.getId();
    }

    @Transactional
    public void update(Long id, TicketInputDto dto) {
        Ticket ticket = modelMapper.map(dto, Ticket.class);
        ticket.setId(id);

        ticketMapper.updateTicket(ticket);
    }

    @Transactional
    public void updateByObjective(Long id, TicketUpdateDto dto) {
        findTicket(id);

        if (dto.getObjective().equals("type")) {
            ticketMapper.updateTicketToType(id, dto.getValue());
        } else if (dto.getObjective().equals("status")) {
            ticketMapper.updateTicketToStatus(id, dto.getValue());
        }

    }

    @Transactional
    public void deleteById(Long id) {
        findTicket(id);

        ticketMapper.deleteTicket(id);
    }

}
