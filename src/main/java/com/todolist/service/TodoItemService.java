package com.todolist.service;

import com.todolist.model.dto.TodoItemDto;
import com.todolist.model.entity.TodoStatus;

import java.util.List;

public interface TodoItemService {
    TodoItemDto createTodoItem(TodoItemDto todoItemDto);

    TodoItemDto updateTodoItemDescription(Long id, String description) throws Exception;

    TodoItemDto markTodoItemAsDone(Long id) throws Exception;

    TodoItemDto markTodoItemAsNotDone(Long id) throws Exception;

    List<TodoItemDto> getAllTodoItems();

    List<TodoItemDto> getAllTodoItemsWithStatus(TodoStatus status);

    TodoItemDto getTodoItemById(Long id) throws Exception;

    void updatePastDueTodoItemStatuses();

}
