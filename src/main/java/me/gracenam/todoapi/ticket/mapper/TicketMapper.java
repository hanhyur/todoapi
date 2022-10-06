package me.gracenam.todoapi.ticket.mapper;

import me.gracenam.todoapi.ticket.dto.TicketDto;
import me.gracenam.todoapi.ticket.dto.TicketListDto;
import me.gracenam.todoapi.ticket.dto.TicketSearchDto;
import me.gracenam.todoapi.ticket.entity.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TicketMapper {

    List<TicketListDto> findTicketList(TicketSearchDto dto);

    Optional<TicketDto> findById(Long id);

    int insertTicket(Ticket ticket);

    int updateTicket(Ticket ticket);

    void updateTicketToStatus(@Param("id") Long id, @Param("status") String status);

    void updateTicketToType(@Param("id") Long id, @Param("type") String type);

    int deleteTicket(Long id);




}
