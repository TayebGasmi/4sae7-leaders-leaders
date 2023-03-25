package com.example.backend.Services;

import com.example.backend.Entity.Qpostion;
import com.example.backend.Entity.Question;
import com.example.backend.Entity.QuestionOrderID;
import com.example.backend.Entity.Quiz;
import com.example.backend.Repository.QuestionOrderRepository;
import com.example.backend.Repository.QuestionRepository;
import com.example.backend.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QuestionOrderService implements IQuestionOrder{
        @Autowired
        QuestionOrderRepository qor;
        @Autowired
        QuestionRepository qr;
        @Autowired
        QuizRepository quizRepository;

        @Override
        public Qpostion addQuestionOrder(Long idQuizz, Long idQuestion, int order) {
            Qpostion qo=new Qpostion();
            Question question=qr.findById(idQuestion).orElse(null);
            Quiz quiz=quizRepository.findById(idQuizz).orElse(null);
            QuestionOrderID questionOrderID=new QuestionOrderID();
            questionOrderID.setQuestion(question);
            questionOrderID.setQuiz(quiz);
            qo.setPostion(order);
            qo.setQuestionOrderId(questionOrderID);
            return qor.save(qo);
        }

        @Override
        public void DeleteQuestionOrder(Long idQuestion,Long idQuiz) {
            QuestionOrderID qoi=new QuestionOrderID();
            Question question=qr.findById(idQuestion).orElse(null);
            Quiz quiz=quizRepository.findById(idQuiz).orElse(null);
            qoi.setQuestion(question);
            qoi.setQuiz(quiz);
            qor.deleteById(qoi);
        }

        @Override
        public Integer getOrder(Long idQuizz, Long idQuestion) {

            return qor.getPostion(idQuizz,idQuestion);
        }

}

