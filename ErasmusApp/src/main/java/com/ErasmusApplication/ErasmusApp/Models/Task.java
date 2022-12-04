package com.ErasmusApplication.ErasmusApp.Models;

import lombok.Data;

@Data
public class Task {
    private String content;
    private String deadline;

    public Task() {
    }

    public Task(String content, String deadline) {
        this.content = content;
        this.deadline = deadline;
    }

}
