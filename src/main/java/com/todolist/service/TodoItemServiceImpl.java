package com.todolist.service;

import com.todolist.exception.CannotMarkPastDueTodoItemAsDoneException;
import com.todolist.exception.CannotMarkPastDueTodoItemAsNotDoneException;
import com.todolist.exception.TodoItemNotFoundException;
import com.todolist.model.dto.TodoItemDto;
import com.todolist.model.entity.TodoItem;
import com.todolist.model.entity.TodoStatus;
import com.todolist.repository.TodoItemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoItemServiceImpl implements TodoItemService {

    private final TodoItemRepository todoItemRepository;

    public TodoItemServiceImpl(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    @Override
    public TodoItemDto createTodoItem(TodoItemDto todoItemDto) {
        TodoItem todoItem = TodoItem.builder()
                .description(todoItemDto.description())
                .status(TodoStatus.NOT_DONE)
                .creationDateTime(LocalDateTime.now())
                .dueDateTime(todoItemDto.dueDateTime() != null ?
                    todoItemDto.doneDateTime() : LocalDateTime.now())
                .build();

        todoItem = todoItemRepository.save(todoItem);
        return mapToDTO(todoItem);
    }

    @Override
    public TodoItemDto updateTodoItemDescription(Long id, String description) throws TodoItemNotFoundException {
        TodoItem todoItem = todoItemRepository.findById(id).orElseThrow(() -> new TodoItemNotFoundException(id));
        todoItem.setDescription(description);
        todoItem = todoItemRepository.save(todoItem);
        return mapToDTO(todoItem);
    }

    @Override
    public TodoItemDto markTodoItemAsDone(Long id) throws Exception {
        TodoItem todoItem = todoItemRepository.findById(id).orElseThrow(() -> new TodoItemNotFoundException(id));
        if (todoItem.getStatus().equals(TodoStatus.PAST_DUE)) {
            throw new CannotMarkPastDueTodoItemAsDoneException(id);
        }
        todoItem.setStatus(TodoStatus.DONE);
        todoItem.setDoneDateTime(LocalDateTime.now());
        todoItem = todoItemRepository.save(todoItem);
        return mapToDTO(todoItem);
    }

    @Override
    public TodoItemDto markTodoItemAsNotDone(Long id) throws Exception {
        TodoItem todoItem = todoItemRepository.findById(id).orElseThrow(() -> new TodoItemNotFoundException(id));
        if (todoItem.getStatus().equals(TodoStatus.PAST_DUE)) {
            throw new CannotMarkPastDueTodoItemAsNotDoneException(id);
        }
        todoItem.setStatus(TodoStatus.NOT_DONE);
        todoItem.setDoneDateTime(null);
        todoItem = todoItemRepository.save(todoItem);
        return mapToDTO(todoItem);
    }

    @Override
    public List<TodoItemDto> getAllTodoItems() {
        List<TodoItem> todoItems = todoItemRepository.findAll();
        return todoItems.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TodoItemDto> getAllTodoItemsWithStatus(TodoStatus status) {
        List<TodoItem> todoItems = todoItemRepository.findAllByStatus(status);
        return todoItems.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TodoItemDto getTodoItemById(Long id) throws Exception {
        return mapToDTO(todoItemRepository.findById(id).orElseThrow(() -> new TodoItemNotFoundException(id)));
    }


    private TodoItemDto mapToDTO(TodoItem todoItem) {
        return TodoItemDto
                .builder()
                .id(todoItem.getId())
                .description(todoItem.getDescription())
                .status(todoItem.getStatus().name())
                .creationDateTime(todoItem.getCreationDateTime())
                .dueDateTime(todoItem.getDueDateTime())
                .build();
    }
}
