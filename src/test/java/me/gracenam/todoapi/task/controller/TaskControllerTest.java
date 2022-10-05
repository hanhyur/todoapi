package me.gracenam.todoapi.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.gracenam.todoapi.task.dto.TaskInputDto;
import me.gracenam.todoapi.task.dto.TaskSearchDto;
import me.gracenam.todoapi.task.dto.TaskUpdateDto;
import me.gracenam.todoapi.task.enums.TaskStatus;
import me.gracenam.todoapi.task.enums.TaskType;
import me.gracenam.todoapi.task.service.TaskService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TaskService taskService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Task 등록 테스트")
    public void createTaskTest() throws Exception {
        TaskInputDto taskInput = getTaskInputDto();

        mockMvc.perform(post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskInput)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Task 조회 테스트")
    public void findTaskTest() throws Exception {

        TaskInputDto taskInput = getTaskInputDto();

        Long id = taskService.save(taskInput);

        mockMvc.perform(get("/api/todo/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value("Test Task"))
                .andExpect(jsonPath("contents").value("This Task is Testing Content"));
    }

    @Test
    @DisplayName("없는 Task 조회")
    public void findTaskTest_NotFound() throws Exception {
        // When & Then
        this.mockMvc.perform(get("/api/todo/{id}", 99999))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;
    }

    @Test
    @DisplayName("Task 목록 조회 테스트")
    public void findTaskListTest() throws Exception {
        for (int i = 1; i <= 100; i++) {
            TaskInputDto taskInput = getTaskInputDto(i);
            taskService.save(taskInput);
        }

        TaskSearchDto dto = new TaskSearchDto();
        dto.setContents("Testing");

        mockMvc.perform(get("/api/todo")
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Task 수정 테스트")
    public void updateTaskTest() throws Exception {
        TaskInputDto dto = getTaskInputDto();
        Long id = taskService.save(dto);

        dto.setTitle("task update test");
        dto.setContents("this content is updated");

        mockMvc.perform(put("/api/todo/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("Task 상태 수정 테스트")
    public void updateStatusTest() throws Exception {
        TaskInputDto dto = getTaskInputDto();
        Long id = taskService.save(dto);

        TaskUpdateDto updateDto = TaskUpdateDto.builder()
                .objective("status")
                .value(TaskStatus.DOING.name())
                .build();

        mockMvc.perform(patch("/api/todo/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("Task 타입 수정 테스트")
    public void updateTypeTest() throws Exception {
        TaskInputDto dto = getTaskInputDto();
        Long id = taskService.save(dto);

        TaskUpdateDto updateDto = TaskUpdateDto.builder()
                .objective("type")
                .value(TaskType.HOBBY.name())
                .build();

        mockMvc.perform(patch("/api/todo/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("Task 삭제 테스트")
    public void deleteTaskMockTest() throws Exception {

        for(int i = 1; i <= 50; i++){
            TaskInputDto dto = getTaskInputDto(i);
            taskService.save(dto);
        }

        mockMvc.perform(delete("/api/todo/{id}", 25))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private static TaskInputDto getTaskInputDto() {
        TaskInputDto dto = TaskInputDto.builder()
                .title("Test Task")
                .contents("This Task is Testing Content")
                .type(TaskType.WORK.name())
                .build();

        return dto;
    }

    private static TaskInputDto getTaskInputDto(int index) {
        TaskInputDto dto = TaskInputDto.builder()
                .title("Test Task")
                .contents("This Task is Testing Content " + index)
                .type(TaskType.WORK.name())
                .build();

        return dto;
    }

}