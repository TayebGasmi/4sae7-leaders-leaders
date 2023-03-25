package com.example.backend.Services;


import com.example.backend.Entity.Qpostion;

public interface IQuestionOrder {
    public Qpostion addQuestionOrder(Long idQuizz, Long idQuestion, int order);
    public void DeleteQuestionOrder(Long idQuestion,Long idQuiz);
    public Integer getOrder(Long idQuizz,Long idQuestion);
}
