package com.todolist.model.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TodoItemDto(
        Long id,
        String description,
        String status,
        LocalDateTime creationDateTime,
        LocalDateTime dueDateTime,
        LocalDateTime doneDateTime
) {
}