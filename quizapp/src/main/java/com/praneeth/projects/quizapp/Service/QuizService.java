package com.praneeth.projects.quizapp.Service;

import com.praneeth.projects.quizapp.dao.QuestionDao;
import com.praneeth.projects.quizapp.dao.QuizDao;
import com.praneeth.projects.quizapp.model.Question;
import com.praneeth.projects.quizapp.model.QuestionWrapper;
import com.praneeth.projects.quizapp.model.Quiz;
import com.praneeth.projects.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDao.findRandomQuestionByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizDao.save(quiz);

        int quizId = quizDao.findIdByTitle(title);

        return new ResponseEntity<>("Quiz created with id " + quizId, HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {

        Optional<Quiz> quiz = quizDao.findById(id);

        List<QuestionWrapper> questionsForUser = new ArrayList<QuestionWrapper>() ;

        for(Question q : quiz.get().getQuestions()){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();

        List<Question> questions = quiz.getQuestions();
        int result=0;
        int len = questions.size();

        for(int i=0; i<len; i++){
            if(questions.get(i).getRightAnswer().equals(responses.get(i).getUserAnswer())){
                result++;
            }
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
