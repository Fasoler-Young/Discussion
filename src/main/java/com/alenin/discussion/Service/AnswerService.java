package com.alenin.discussion.Service;

import com.alenin.discussion.Entity.AnswerEntity;
import com.alenin.discussion.Repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    private final AnswerRepository repository;

    public AnswerService(AnswerRepository repository) {
        this.repository = repository;
    }

    public AnswerEntity add(AnswerEntity answer){
        return repository.save(answer);
    }

    public AnswerEntity update(AnswerEntity answer){
        return repository.save(answer);
    }

    public void delete(AnswerEntity answer){
        repository.delete(answer);
    }


}
