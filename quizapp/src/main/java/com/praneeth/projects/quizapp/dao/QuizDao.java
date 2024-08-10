package com.praneeth.projects.quizapp.dao;

import com.praneeth.projects.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {

    @Query(value = "Select id from quiz q where q.title=:title", nativeQuery = true)
    int findIdByTitle(String title);
}
