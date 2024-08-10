package com.praneeth.projects.quizapp.dao;

import com.praneeth.projects.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    public List<Question> findQuestionByCategory(String category);

    @Query(value = "SELECT * FROM QUESTION q WHERE q.CATEGORY=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionByCategory(String category, int numQ);
}
