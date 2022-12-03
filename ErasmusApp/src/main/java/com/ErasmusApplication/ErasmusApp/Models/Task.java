package com.ErasmusApplication.ErasmusApp.Models;

public class Task {
    private String content;
    private String deadline;

    public Task() {
    }

    public Task(String content, String deadline) {
        this.content = content;
        this.deadline = deadline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
