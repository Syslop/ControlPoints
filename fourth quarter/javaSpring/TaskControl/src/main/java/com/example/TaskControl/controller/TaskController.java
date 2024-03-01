package com.example.TaskControl.controller;

import com.example.TaskControl.dto.Task;
import com.example.TaskControl.service.TaskService;
import com.example.TaskControl.util.TaskBuilder;
import com.example.TaskControl.util.TaskFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;
    private final TaskBuilder taskBuilder;
    private final TaskFacade taskFacade;

    @Autowired
    public TaskController(TaskService taskService, TaskFacade taskFacade) {
        this.taskService = taskService;
        this.taskFacade = taskFacade;
        this.taskBuilder = new TaskBuilder();
    }

    @GetMapping("/task/list")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("/task/add")
    public void createTask(@RequestBody Task task) {
        taskFacade.createTask(task.getName(), task.getComment());
    }

    @PostMapping("/task/add/name")
    public void createTask(@RequestBody String name) {
        taskBuilder.setName(name);
        taskBuilder.setComment("Задача создана через паттерн строитель");

        taskService.createTask(taskBuilder.build());
    }
}