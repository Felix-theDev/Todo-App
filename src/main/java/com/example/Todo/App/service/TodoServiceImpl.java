package com.example.Todo.App.service;

import com.example.Todo.App.dao.TodoRepository;
import com.example.Todo.App.entity.Todo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{

    private TodoRepository todoRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    @Override
    public List<Todo> findByDescription(String description) {
        return todoRepository.searchByDescription(description);
    }

    @Override
    public Todo getById(int id) {
        return todoRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        todoRepository.deleteById(Integer.valueOf(id));
    }

    @Override
    public void deleteByDescription(String description) {
        try{
            todoRepository.deleteByDescription(description);
            System.out.println("Hello 2");
        }catch (EntityNotFoundException exception){
            System.out.println("Hello 3");
            throw new EntityNotFoundException("Error");
        }
    }

    @Override
    public void saveTodo(Todo todo) {
        todoRepository.save(todo);
    }

    @Override
    public Todo changeStatus(int id, boolean isDone) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);

        if(optionalTodo.isPresent()){
            Todo todo = optionalTodo.get();
            todo.setIs_done(isDone);
            return todoRepository.save(todo);

        }else {
            return null;
        }

    }

    @Override
    public List<Todo> filterByStatus(boolean status) {
        List<Todo> todos = todoRepository.findByCompletionStatus(status);

        return !todos.isEmpty() ? todos : null;
    }
}
