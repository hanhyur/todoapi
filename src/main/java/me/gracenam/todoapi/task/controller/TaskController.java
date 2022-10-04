package me.gracenam.todoapi.task.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.gracenam.todoapi.task.dto.TaskDto;
import me.gracenam.todoapi.task.dto.TaskSearchDto;
import me.gracenam.todoapi.task.dto.TaskUpdateDto;
import me.gracenam.todoapi.task.entity.Task;
import me.gracenam.todoapi.task.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getTaskList() {
        return ResponseEntity.ok(taskService.getTaskList());
    }

    @PostMapping
    public ResponseEntity<List<Task>> createTask(@RequestBody TaskDto taskDto) {

        taskService.save(taskDto);

        return ResponseEntity.ok(taskService.getTaskList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> searchTask(@RequestBody TaskSearchDto taskSearchDto) {
        taskService.findByContents(taskSearchDto);

        return ResponseEntity.ok(taskService.findByContents(taskSearchDto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@RequestBody TaskUpdateDto taskUpdateDto) {

        taskService.update(taskUpdateDto.getId(), taskUpdateDto);

        return ResponseEntity.ok(taskService.findById(taskUpdateDto.getId()));
    }

    @PostMapping("/{id}")
    public ResponseEntity<List<Task>> deleteTask(@PathVariable Long id) {

        taskService.delete(id);

        return ResponseEntity.ok(taskService.getTaskList());
    }
}
