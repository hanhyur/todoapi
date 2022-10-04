package me.gracenam.todoapi.task.service;

import lombok.RequiredArgsConstructor;
import me.gracenam.todoapi.task.dto.TaskDto;
import me.gracenam.todoapi.task.dto.TaskSearchDto;
import me.gracenam.todoapi.task.dto.TaskUpdateDto;
import me.gracenam.todoapi.task.entity.Task;
import me.gracenam.todoapi.task.exception.TaskEmptyException;
import me.gracenam.todoapi.task.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    @Autowired
    private TaskMapper taskMapper;

    public List<Task> getTaskList() {
        List<Task> taskLists = taskMapper.findAll();

        return taskLists;
    }

    public Task findById(Long id) {
        Task task = taskMapper.findById(id)
                .orElseThrow(() -> new TaskEmptyException(id));

        return task;
    }

    public Task findByContents(TaskSearchDto taskSearchDto) {
        Task task = taskMapper.findByContents(taskSearchDto)
                .orElseThrow(() -> new TaskEmptyException(taskSearchDto.getId()));

        return task;
    }


    public void save(TaskDto taskDto) {
        Task task = Task.builder()
                .title(taskDto.getTitle())
                .contents(taskDto.getContents())
                .taskType(taskDto.getTaskType())
                .taskStatus(taskDto.getTaskStatus())
                .build();

        taskMapper.save(task);
    }

    public void update(Long id, TaskUpdateDto taskUpdateDto) {
        taskMapper.update(id, taskUpdateDto);
    }

    public void delete(Long id) {
        taskMapper.delete(id);
    }

}
