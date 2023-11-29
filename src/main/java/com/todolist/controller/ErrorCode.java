package com.todolist.controller;

import lombok.Builder;

import java.util.List;

@Builder
public record ErrorCode (
    int code,
    String message,
    List<String> errors
)
{}
