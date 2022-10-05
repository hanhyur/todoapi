package me.gracenam.todoapi.task.mapper;

import me.gracenam.todoapi.task.dto.TaskDto;
import me.gracenam.todoapi.task.dto.TaskListDto;
import me.gracenam.todoapi.task.dto.TaskSearchDto;
import me.gracenam.todoapi.task.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TaskMapper {

    List<TaskListDto> findTaskList(TaskSearchDto taskSearchDto);

    Optional<TaskDto> findById(Long id);

    int insertTask(Task task);

    int updateTask(Task task);

    void updateTaskToStatus(@Param("id") Long id, @Param("status") String status);

    void updateTaskToType(@Param("id") Long id, @Param("type") String type);

    int deleteTask(Long id);




}
