package com.todolist.repository;

import com.todolist.model.entity.TodoItem;
import com.todolist.model.entity.TodoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
    List<TodoItem> findAllByStatus(TodoStatus status);
}