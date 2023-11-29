package com.todolist.controller;

import com.todolist.exception.CannotMarkPastDueTodoItemAsDoneException;
import com.todolist.exception.CannotMarkPastDueTodoItemAsNotDoneException;
import com.todolist.exception.TodoItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorCode> handleException(Exception ex) {
        ErrorCode errorCode = ErrorCode
                .builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorCode);
    }

    @ExceptionHandler({CannotMarkPastDueTodoItemAsNotDoneException.class, CannotMarkPastDueTodoItemAsDoneException.class})
    public ResponseEntity<ErrorCode> handleBadRequestByExceptionOperation(Exception ex) {
        ErrorCode errorCode = ErrorCode
                .builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();

        return ResponseEntity.badRequest().body(errorCode);
    }

    @ExceptionHandler(TodoItemNotFoundException.class)
    public ResponseEntity<ErrorCode> handleNotFoundOperation(TodoItemNotFoundException ex) {
        ErrorCode errorCode = ErrorCode
                .builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorCode);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorCode> handleValidationExceptionsOperation(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<String> errors = result.getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorCode errorResponse = ErrorCode
                .builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Validation failed")
                .errors(errors)
                .build();

        return ResponseEntity.badRequest().body(errorResponse);
    }
}
