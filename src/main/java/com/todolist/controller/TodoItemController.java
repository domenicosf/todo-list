package com.todolist.controller;

import com.todolist.model.dto.DescriptionDto;
import com.todolist.model.dto.TodoItemDto;
import com.todolist.service.TodoItemService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.todolist.model.entity.TodoStatus.NOT_DONE;

@RestController
@RequestMapping("/todo-items")
@OpenAPIDefinition(
        info = @Info(
                title = "Todo Item List Api",
                description = "" +
                        "This Api Allow the creation, modification and list of todo items")
)
@Tag(name="Todo Api", description = "This Api Allow the creation, modification and list of todo items")
public class TodoItemController {

    private final TodoItemService todoItemService;

    public TodoItemController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @PostMapping
    @Operation(description = "Create a new todo item",
    responses = {
            @ApiResponse(responseCode = "200", description = "Todo item created"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public TodoItemDto addItem(@Validated  @RequestBody TodoItemDto itemDto) {
        return todoItemService.createTodoItem(itemDto);
    }

    @PatchMapping("/{id}")
    public TodoItemDto changeDescription(@PathVariable Long id, @Validated DescriptionDto descriptionDto) throws Exception {
        return todoItemService.updateTodoItemDescription(id, descriptionDto.newDescription());
    }


    @PatchMapping("{id}/done")
    public TodoItemDto markItemAsDone(@PathVariable Long id) throws Exception {
        return todoItemService.markTodoItemAsDone(id);
    }

    @PatchMapping("{id}/not-done")
    public TodoItemDto markItemAsNotDone(@PathVariable Long id) throws Exception {
        return todoItemService.markTodoItemAsNotDone(id);
    }

    @GetMapping("/not-done")
    public List<TodoItemDto> listAllItemsMarkedAsNotDone() {
        return todoItemService.getAllTodoItemsWithStatus(NOT_DONE);
    }

    @GetMapping("/{id}")
    public TodoItemDto getItemDetails(@PathVariable Long id) throws Exception {
        return todoItemService.getTodoItemById(id);
    }

}
