package com.todolist.exception;

public class CannotMarkPastDueTodoItemAsNotDoneException extends Exception {
    public CannotMarkPastDueTodoItemAsNotDoneException(Long id) {
        super(String.format("The item <id = %d> cannot be marked as not done, because it is already marked as done", id));
    }
}
