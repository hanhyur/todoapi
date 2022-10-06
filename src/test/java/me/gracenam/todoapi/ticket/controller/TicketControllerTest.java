package me.gracenam.todoapi.ticket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.gracenam.todoapi.ticket.dto.TicketInputDto;
import me.gracenam.todoapi.ticket.dto.TicketSearchDto;
import me.gracenam.todoapi.ticket.dto.TicketUpdateDto;
import me.gracenam.todoapi.ticket.enums.TicketStatus;
import me.gracenam.todoapi.ticket.enums.TicketType;
import me.gracenam.todoapi.ticket.service.TicketService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class TicketControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TicketService ticketService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Ticket 등록 테스트")
    public void createTicketTest() throws Exception {
        TicketInputDto inputDto = getTicketInputDto();

        mockMvc.perform(post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andDo(print())
                .andDo(document("createTicket",
                        links(
                                linkWithRel("self").description("task create api")
                        )))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("_links.self").exists())
        ;
    }

    @Test
    @DisplayName("Ticket 조회 테스트")
    public void findTicketTest() throws Exception {

        TicketInputDto inputDto = getTicketInputDto();

        Long id = ticketService.save(inputDto);

        mockMvc.perform(get("/api/todo/{id}", id))
                .andDo(print())
                .andDo(document("findTicket"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("Test Ticket"))
                .andExpect(jsonPath("contents").value("This Ticket is Testing Content"));
    }

    @Test
    @DisplayName("없는 Ticket 조회")
    public void findTicketTest_NotFound() throws Exception {
        // When & Then
        this.mockMvc.perform(get("/api/todo/{id}", 99999))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;
    }

    @Test
    @DisplayName("Ticket 목록 조회 테스트")
    public void findTicketListTest() throws Exception {
        for (int i = 1; i <= 100; i++) {
            TicketInputDto inputDto = TicketControllerTest.getTicketInputDto(i);
            ticketService.save(inputDto);
        }

        TicketSearchDto dto = new TicketSearchDto();
        dto.setContents("Testing");

        mockMvc.perform(get("/api/todo")
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andDo(document("findTicketList"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Ticket 수정 테스트")
    public void updateTicketTest() throws Exception {
        TicketInputDto dto = getTicketInputDto();
        Long id = ticketService.save(dto);

        dto.setTitle("Ticket update test");
        dto.setContents("this content is updated");

        mockMvc.perform(put("/api/todo/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(jsonPath("_links.self").exists())
                .andDo(document("updateTicket"))
        ;
    }

    @Test
    @DisplayName("Ticket 상태 수정 테스트")
    public void updateStatusTest() throws Exception {
        TicketInputDto dto = getTicketInputDto();
        Long id = ticketService.save(dto);

        TicketUpdateDto updateDto = TicketUpdateDto.builder()
                .objective("status")
                .value(TicketStatus.DOING.name())
                .build();

        mockMvc.perform(patch("/api/todo/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("Ticket 타입 수정 테스트")
    public void updateTypeTest() throws Exception {
        TicketInputDto dto = getTicketInputDto();
        Long id = ticketService.save(dto);

        TicketUpdateDto updateDto = TicketUpdateDto.builder()
                .objective("type")
                .value(TicketType.HOBBY.name())
                .build();

        mockMvc.perform(patch("/api/todo/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("Ticket 삭제 테스트")
    public void deleteTicketMockTest() throws Exception {

        for (int i = 1; i <= 50; i++) {
            TicketInputDto dto = TicketControllerTest.getTicketInputDto(i);
            ticketService.save(dto);
        }

        mockMvc.perform(delete("/api/todo/{id}", 25))
                .andDo(print())
                .andDo(document("deleteTicket"))
                .andExpect(status().isNoContent());
    }

    private static TicketInputDto getTicketInputDto() {
        TicketInputDto dto = TicketInputDto.builder()
                .title("Test Ticket")
                .contents("This Ticket is Testing Content")
                .type(TicketType.WORK.name())
                .build();

        return dto;
    }

    private static TicketInputDto getTicketInputDto(int index) {
        TicketInputDto dto = TicketInputDto.builder()
                .title("Test Ticket")
                .contents("This Ticket is Testing Content " + index)
                .type(TicketType.WORK.name())
                .build();

        return dto;
    }

}