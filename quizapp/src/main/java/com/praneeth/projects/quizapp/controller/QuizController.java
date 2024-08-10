package com.praneeth.projects.quizapp.controller;

import com.praneeth.projects.quizapp.Service.QuizService;
import com.praneeth.projects.quizapp.model.QuestionWrapper;
import com.praneeth.projects.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @GetMapping("home")
    public ResponseEntity<String> getHome(){
        return new ResponseEntity<>("Welcome to quiz home page", HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("get/{Id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int Id){
        return quizService.getQuizQuestions(Id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }
}
