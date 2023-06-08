package com.alenin.discussion.Service;

import com.alenin.discussion.DTO.QuestionDTO;
import com.alenin.discussion.Entity.QuestionEntity;
import com.alenin.discussion.Repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public QuestionEntity add(QuestionDTO question){
        return repository.save(question.toEntity());
    }

    public QuestionEntity update(QuestionEntity question){
        return repository.save(question);
    }



    public void delete(QuestionEntity question){
        repository.delete(question);
    }

    public QuestionEntity findById(Integer id) {
        Optional<QuestionEntity> question = repository.findById(id);
        return question.orElse(null);
    }

    public Iterable<QuestionEntity> findAll() {
        return repository.findAll();
    }
}
