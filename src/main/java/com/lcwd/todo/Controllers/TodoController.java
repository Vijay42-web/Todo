package com.lcwd.todo.Controllers;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.todo.entities.Todo;
import com.lcwd.todo.services.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController 
{
	Logger logger= LoggerFactory.getLogger(TodoController.class);
	
	@Autowired
	private TodoService todoService;
	
	Random random = new Random();
	
	@PostMapping
	public ResponseEntity<Todo> todoHandler(@RequestBody Todo todo)
	
	{
		int id = random.nextInt(9999999);
		todo.setId(id);	
		//creating todo
		logger.info("create todolist");
		//calling service
		Todo todo1 = todoService.createTodo(todo);
		
		return new ResponseEntity<>(todo1,HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Todo>>getAllTodoHandler ()
	{
		List<Todo> allTodos=todoService.getAllTodos();
		return new ResponseEntity<>(allTodos,HttpStatus.OK);
	}
	
	@GetMapping("{todoId}")
	public ResponseEntity<Todo> getSingleTodoHandler(@PathVariable int todoId)
	{
		Todo todo = todoService.getTodo(todoId);	
		return ResponseEntity.ok(todo);
	}
}
