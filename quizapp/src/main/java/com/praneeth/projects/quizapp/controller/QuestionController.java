package com.praneeth.projects.quizapp.controller;

import com.praneeth.projects.quizapp.Service.QuestionService;
import com.praneeth.projects.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("home")
    public String getHome(){
        return "Hello, Welcome to the quiz let's get started :)";
    }

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{selectedCategory}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String selectedCategory){
        return questionService.getQuestionByCategory(selectedCategory);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @PutMapping("update")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question){
        return questionService.updateQuestion(question);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id){
        return questionService.deleteQuestionById(id);
    }
}
