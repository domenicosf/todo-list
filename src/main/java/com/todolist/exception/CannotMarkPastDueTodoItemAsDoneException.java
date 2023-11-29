package com.todolist.exception;

public class CannotMarkPastDueTodoItemAsDoneException extends Exception {
    public CannotMarkPastDueTodoItemAsDoneException(Long id) {
        super(String.format("The Item <id = %d> was already marked done.", id));
    }
}
