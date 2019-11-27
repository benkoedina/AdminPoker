package com.example.adminpoker.Models;

import java.time.LocalDateTime;

public class Question {
    private int id;
    private String question;
    private String status;
    private LocalDateTime time;


    public Question(int id, String question, String status, LocalDateTime time) {
        this.id = id;
        this.question = question;
        this.status = status;
        this.time = time;
    }
    public Question(){}

    public Question(int id, String question, String status) {
        this.id = id;
        this.question = question;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", status=" + status +
                ", time=" + time +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
