package com.example.Todo.App.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name="target_date")
    private LocalDate target_date;

    @Column (name = "is_done")
    private boolean is_done;

    public Todo() {
    }

    public Todo(int id, String description, LocalDate target_date, boolean is_done) {
        this.id = id;
        this.description = description;
        this.target_date = target_date;
        this.is_done = is_done;
    }

    public Todo(String description, LocalDate target_date, boolean is_done) {
        this.description = description;
        this.target_date = target_date;
        this.is_done = is_done;
    }

    public Todo(String description, LocalDate target_date) {
        this.description = description;
        this.target_date = target_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTarget_date() {
        System.out.println("The date is " + target_date);
        return target_date;
    }

    public void setTarget_date(LocalDate target_date) {
        this.target_date = target_date;
    }

    public boolean getIs_done() {
        return is_done;
    }

    public void setIs_done(boolean is_done) {
        this.is_done = is_done;
    }
}
