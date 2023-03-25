package com.example.backend.Services;


import com.example.backend.Entity.Question;
import com.example.backend.Entity.Quiz;

import java.util.List;
import java.util.Map;

public interface IQuizService {

     Quiz addQuiz();
     void removeQuiz(Long id);
     Quiz getQuiz(Long id);
     List<Quiz> getQuizs();
     Map<Integer, Question> getQuestion(Long id);
}
