package me.gracenam.todoapi.task.controller;

import lombok.RequiredArgsConstructor;
import me.gracenam.todoapi.task.dto.*;
import me.gracenam.todoapi.task.exception.TaskValidException;
import me.gracenam.todoapi.task.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity findTaskList(TaskSearchDto taskSearchDto) {

        List<TaskListDto> taskListDtos = taskService.findTaskList(taskSearchDto);

        return ResponseEntity.ok().body(taskListDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity findTask(@PathVariable Long id) {
        TaskDto taskDto = taskService.findTask(id);

        return ResponseEntity.ok().body(taskDto);
    }

    @PostMapping
    public ResponseEntity createTask(@RequestBody @Valid TaskInputDto taskInputDto,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new TaskValidException(bindingResult);
        }

        Long id = taskService.save(taskInputDto);

        return ResponseEntity.ok().body(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTask(@PathVariable Long id,
                                     @RequestBody TaskInputDto dto) {

        taskService.update(id, dto);

        return ResponseEntity.status(HttpStatus.FOUND).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateObjective(@PathVariable Long id,
                                          @RequestBody TaskUpdateDto dto) {
        taskService.updateByObjective(id, dto);

        return ResponseEntity.status(HttpStatus.FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
