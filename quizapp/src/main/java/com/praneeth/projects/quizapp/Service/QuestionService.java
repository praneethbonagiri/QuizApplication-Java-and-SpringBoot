package com.praneeth.projects.quizapp.Service;

import com.praneeth.projects.quizapp.dao.QuestionDao;
import com.praneeth.projects.quizapp.model.Question;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>>getAllQuestions(){
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<Question>() ,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String selectedCategory) {
        try{
            return new ResponseEntity<>(questionDao.findQuestionByCategory(selectedCategory), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<Question>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try{
            questionDao.save(question);
            return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error while adding question", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteQuestionById(Integer id) {
        try{
            questionDao.deleteById(id);
            return new ResponseEntity<>("Question with Id " + id + " deleted successfully", HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error while deleting question with Id " + id, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateQuestion(Question question) {
        try{
            questionDao.save(question);
            return new ResponseEntity<>("Question updated successfully", HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error while updating", HttpStatus.BAD_REQUEST);
    }
}
