package com.example.TaskControl.util;

import com.example.TaskControl.dto.Task;
import com.example.TaskControl.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskFacade {

    private final TaskService taskService;

    @Autowired
    public TaskFacade(TaskService taskService) {
        this.taskService = taskService;
    }

    public Task createTask(String name, String comment) {
        Task task = new Task();
        task.setName(name);
        task.setComment(comment);
        return taskService.createTask(task);
    }
}
