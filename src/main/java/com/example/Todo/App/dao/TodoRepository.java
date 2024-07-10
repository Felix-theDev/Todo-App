package com.example.Todo.App.dao;

import com.example.Todo.App.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer > {

    @Query("SELECT u FROM Todo u WHERE u.description LIKE %:description%")
    List<Todo> searchByDescription(@Param("description") String description);
//    List<Todo> findByDescriptionContaining(String description);


    @Modifying
    @Transactional
    @Query("DELETE FROM Todo s WHERE s.description = ?1")
    void deleteByDescription(String description);

    @Query("SELECT u from Todo u WHERE u.is_done = :isDone")
    List<Todo> findByCompletionStatus(@Param("isDone") boolean isDone);
//  List<Todo> findByIsDone(boolean isDone);

}
