package com.todolist.exception;

public class TodoItemNotFoundException extends Exception {
    public TodoItemNotFoundException(Long id) {
        super(String.format("%d id not found", id));
    }
}
