package com.crud.tasks.mapper;


import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        //When
        Task theTask = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(1L, theTask.getId());
        assertEquals("title", theTask.getTitle());
        assertEquals("content", theTask.getContent());
    }

    @Test
    public void testMapNoArgTaskToTaskDto() {
        //Given
        Task task = new Task();
        //When
        TaskDto theTaskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertNotNull(theTaskDto);
        assertNull(theTaskDto.getId());
        assertNull(theTaskDto.getTitle());
        assertNull(theTaskDto.getContent());
    }

    @Test
    public void testMapToTaskDto () {
        //Given
        Task task = new Task(1L, "title", "content");
        //When
        TaskDto theTaskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(1L, theTaskDto.getId());
        assertEquals("title", theTaskDto.getTitle());
        assertEquals("content", theTaskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoEmptyList() {
        //Given
        List<Task> tasks = List.of();
        //When
        List<TaskDto> theTasks = taskMapper.mapToTaskDtoList(tasks);
        //Then
        assertNotNull(theTasks);
        assertEquals(0, theTasks.size());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        List<Task> tasks = List.of(new Task(1L, "title", "content"));
        //When
        List<TaskDto> theTasks = taskMapper.mapToTaskDtoList(tasks);
        //Then
        assertNotNull(theTasks);
        assertEquals(1, theTasks.size());
    }
}
