package com.todolist.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.todolist.model.entity.TodoStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TodoItemDto(
        @Schema(description = "The unique identifier of the todo item")
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long id,
        @Schema(description = "The description of the todo item")
        @NotEmpty
        String description,
        @Schema(description = "The status of the todo item", implementation = TodoStatus.class)
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        String status,
        @Schema(description = "The creation date")
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime creationDateTime,
        @Schema(description = "The due date")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime dueDateTime,
        @Schema(description = "The done date")
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime doneDateTime
) {
}