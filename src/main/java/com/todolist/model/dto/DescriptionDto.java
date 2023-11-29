package com.todolist.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record DescriptionDto(
        @NotEmpty
        String newDescription)
{
}
