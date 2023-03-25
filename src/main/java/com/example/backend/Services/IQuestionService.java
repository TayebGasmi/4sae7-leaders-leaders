package com.example.backend.Services;


import com.example.backend.Entity.Question;

import java.util.List;

public interface IQuestionService {
    public Question addQuestion(Question Q);
    public void removeQuestion(Long id);
    public Question updateQuestion(Question Q);
    public Question getQuestion(Long id);
    public List<Question> getQuestions();
}
