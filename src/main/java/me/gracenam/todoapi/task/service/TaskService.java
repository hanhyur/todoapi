package me.gracenam.todoapi.task.service;

import lombok.RequiredArgsConstructor;
import me.gracenam.todoapi.task.dto.*;
import me.gracenam.todoapi.task.entity.Task;
import me.gracenam.todoapi.task.enums.TaskStatus;
import me.gracenam.todoapi.task.exception.TaskNotFoundException;
import me.gracenam.todoapi.task.mapper.TaskMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskMapper taskMapper;

    private final ModelMapper modelMapper;

    public List<TaskListDto> findTaskList(TaskSearchDto dto) {
        List<TaskListDto> taskListDtos = taskMapper.findTaskList(dto);

        return taskListDtos;
    }

    public TaskDto findTask(Long id) {
        Optional<TaskDto> taskDto = taskMapper.findById(id);

        if (!taskDto.isPresent()) {
            throw new TaskNotFoundException(id);
        }

        return taskDto.get();
    }

    public Long save(TaskInputDto taskDto) {
        Task task = modelMapper.map(taskDto, Task.class);
        task.setStatus(TaskStatus.TODO.name());

        taskMapper.insertTask(task);

        return task.getId();
    }

    public void update(Long id, TaskInputDto taskInputDto) {
        Task task = modelMapper.map(taskInputDto, Task.class);
        task.setId(id);

        taskMapper.updateTask(task);
    }

    public void updateByObjective(Long id, TaskUpdateDto taskUpdateDto) {

        findTask(id);

        if (taskUpdateDto.getObjective().equals("type")) {
            taskMapper.updateTaskToType(id, taskUpdateDto.getValue());
        } else if (taskUpdateDto.getObjective().equals("status")) {
            taskMapper.updateTaskToStatus(id, taskUpdateDto.getValue());
        }

    }

    public void deleteById(Long id) {
        findTask(id);

        taskMapper.deleteTask(id);
    }

}
