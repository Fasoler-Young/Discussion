package com.alenin.discussion.Service;

import com.alenin.discussion.Entity.QuestionEntity;
import com.alenin.discussion.Repository.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    private final QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public QuestionEntity add(QuestionEntity question){
        return repository.save(question);
    }

    public QuestionEntity update(QuestionEntity question){
        return repository.save(question);
    }

    public void delete(QuestionEntity question){
        repository.delete(question);
    }
    
}
