package com.example.backend.Services;

import com.example.backend.Entity.Question;
import com.example.backend.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService implements IQuestionService {
    @Autowired
    QuestionRepository qr;

    @Override
    public Question addQuestion(Question q) {
        return qr.save(q);
    }

    @Override
    public void removeQuestion(Long id) {
           qr.deleteById(id);
    }

    @Override
    public Question updateQuestion(Question q) {
        return qr.save(q);
    }

    @Override
    public Question getQuestion(Long id) {
        return qr.findById(id).orElse(null);
    }

    @Override
    public List<Question> getQuestions() {
        return (List<Question>) qr.findAll();
    }
}
