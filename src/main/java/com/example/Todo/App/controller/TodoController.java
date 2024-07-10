package com.example.Todo.App.controller;

import com.example.Todo.App.entity.Todo;
import com.example.Todo.App.service.TodoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/todo")
public class TodoController {

    TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }



    @GetMapping("/todos")
    public String getAll(Model model){

        List<Todo> todos = todoService.getAll();
        model.addAttribute("todos", todos);

        return "todo-list";
    }
    @GetMapping("/search")
    public ResponseEntity<List<Todo>> searchTodo(@RequestParam("description")String description){

        List<Todo> todos = todoService.findByDescription(description);

        return ResponseEntity.ok(todos);

    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Todo> searchById(@PathVariable int id){

        Todo todo = todoService.getById(id);

        return todo!= null ? new ResponseEntity<>(todo, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteByDescription(@RequestParam("description")String description){

        try {
            todoService.deleteByDescription(description);
            System.out.println("Successfully deleted Todo with description: " + description);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            System.out.println("Todo not found with description: " + description);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println("Error occurred while deleting Todo with description: " + description);
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @Transactional
    @GetMapping("deleteTodo/{id}")
    public String deleteById(@PathVariable int id){
        todoService.deleteById(id);

        return "redirect:/todo/todos";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(@ModelAttribute ("todo") Todo todo){

        todoService.saveTodo(todo);

        return "redirect:/todo/todos";

    }

    @PutMapping("/update/{id}/isDone")
    public ResponseEntity<Todo> updateIsDone(@PathVariable int id, @RequestParam boolean isDone){

        Todo todo = todoService.changeStatus(id, isDone);

        return todo!= null ? new  ResponseEntity<>(todo, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/status")
    public ResponseEntity<List<Todo>> getByStatus(@RequestParam boolean isDone){
        List<Todo> todos = todoService.filterByStatus(isDone);

        return todos!= null ? new  ResponseEntity<>(todos, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }

    @GetMapping("/showFormForAddOrUpdate")
    public String showForm(@RequestParam (name = "todoId", required = false) Integer todoId, Model model){
        Todo todo;
        if(todoId != null){

            todo = todoService.getById(todoId);
        }else {
            todo = new Todo();
        }


        model.addAttribute("todo", todo);

        return "todo-form";
    }






//    @GetMapping("/todos")
//    public ResponseEntity<List<Todo>> getAll(){
//
//        List<Todo> todos = todoService.getAll();
//
//        return todos!= null ?new ResponseEntity<>(todos, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

//    @PostMapping("/todos")
//    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo){
//
//        todoService.saveTodo(todo);
//
//        return new ResponseEntity<>(todo, HttpStatus.OK );
//    }

}
