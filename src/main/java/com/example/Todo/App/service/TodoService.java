package com.example.Todo.App.service;

import com.example.Todo.App.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TodoService {

    //    Create a Todo Item (POST)

//    Get a Todo Item by ID (GET)
//    Update a Todo Item (PUT)
//    Delete a Todo Item (DELETE)
//Get Paginated Todo Items (GET)
//    Get Sorted Todo Items (GET)
//    Search Todo Items by Description (GET)

//     Batch Create Todo Items (POST)
//    Batch Update Todo Items (PUT)
//    Batch Delete Todo Items (DELETE)
//


    //    Get All Todo Items (GET)
    List<Todo> getAll();


    List<Todo> findByDescription(String description);

    Todo getById(int id);

    void deleteById(int id);
    void deleteByDescription(String description);

    void saveTodo(Todo todo);

    Todo changeStatus(int id, boolean value);

    //    Filter Todo Items by Completion Status (GET)
    List<Todo> filterByStatus(boolean status);
}
