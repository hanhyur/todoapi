package me.gracenam.todoapi.task.mapper;

import me.gracenam.todoapi.task.dto.TaskSearchDto;
import me.gracenam.todoapi.task.dto.TaskUpdateDto;
import me.gracenam.todoapi.task.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TaskMapper {

    void save(Task task);

    void update(@Param("id") Long id, @Param("updateParam") TaskUpdateDto taskUpdateDto);

    @Select("select * from task")
    List<Task> findAll();

    @Select("select * from task where id=#{id}")
    Optional<Task> findById(Long id);

    Optional<Task> findByContents(@Param("searchParam") TaskSearchDto taskSearchDto);

    void delete(@Param("id") Long id);
}
