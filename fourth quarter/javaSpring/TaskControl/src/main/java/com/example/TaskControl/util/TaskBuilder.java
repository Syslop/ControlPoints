package com.example.TaskControl.util;

import com.example.TaskControl.dto.Task;

public class TaskBuilder {

    private final Task task;

    public TaskBuilder() {
        this.task = new Task();
    }

    public TaskBuilder setName(String name) {
        task.setName(name);
        return this;
    }

    public TaskBuilder setComment(String comment) {
        task.setComment(comment);
        return this;
    }

    public Task build() {
        return task;
    }
}