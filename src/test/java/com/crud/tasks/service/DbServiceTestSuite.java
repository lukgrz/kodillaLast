package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DbServiceTestSuite {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void shouldReturnEmptyTaskList() {
        //Given
        when(taskRepository.findAll()).thenReturn(List.of());
        //When
        List<Task> theList = dbService.getAllTasks();
        //Then
        assertNotNull(theList);
        assertEquals(0, theList.size());
    }

    @Test
    public void shouldReturnList() {
        //Given
        List<Task> tasks = List.of(new Task(1L, "title", "content"));
        when(taskRepository.findAll()).thenReturn(tasks);
        //When
        List<Task> theList = dbService.getAllTasks();
        //then
        assertNotNull(theList);
        assertEquals(1,theList.size());
    }

    @Test
    public void shouldReturnNull () {
        //Given
        Long id = 1L;
        when(taskRepository.findById(id)).thenReturn(null);
        //When
        Optional<Task> theTask = dbService.getTask(id);
        //Then
        assertNull(theTask);
    }

    @Test
    public void shouldReturnTask () {
        //Given
        Long id = 1L;
        Task task = new Task(1L, "title", "content");
        when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        //When
        Optional<Task> theTask = dbService.getTask(id);
        //Then
        assertEquals(task, theTask.get());
    }

    @Test
    public void testSaveTask() {
        //Given
        Task task = new Task(1L, "title", "content");
        when(taskRepository.save(task)).thenReturn(task);
        //When
        Task theTask = dbService.saveTask(task);
        //Then
        assertEquals(task, theTask);
    }

    @Test
    public void testDeleteTask() {
    }
}
