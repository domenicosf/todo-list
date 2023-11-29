package com.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestTodoListApplication {

    public static void main(String[] args) {
        SpringApplication.from(TodoListApplication::main).with(TestTodoListApplication.class).run(args);
    }

}
