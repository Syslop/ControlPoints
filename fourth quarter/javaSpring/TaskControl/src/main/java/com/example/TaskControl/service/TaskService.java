package com.example.TaskControl.service;

import com.example.TaskControl.dto.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();

    Task createTask(Task task);

    Task getTaskById(Long id);

    Task updateTask(Long id, Task task);

    void deleteTask(Long id);

    void addTask(Task task);
}
