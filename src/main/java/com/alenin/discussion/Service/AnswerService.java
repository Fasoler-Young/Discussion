package com.alenin.discussion.Service;

import com.alenin.discussion.DTO.AnswerDTO;
import com.alenin.discussion.Entity.AnswerEntity;
import com.alenin.discussion.Repository.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerService {

    private final AnswerRepository repository;

    public AnswerService(AnswerRepository repository) {
        this.repository = repository;
    }

    public AnswerEntity add(AnswerDTO answerDTO){
        return repository.save(answerDTO.toEntity());
    }

    public AnswerEntity update(AnswerEntity answer){
        return repository.save(answer);
    }

    public void delete(AnswerEntity answer){
        repository.delete(answer);
    }


    public AnswerEntity findById(Integer id) {
        Optional<AnswerEntity> answer = repository.findById(id);

        return answer.orElse(null);
    }

    public Iterable<AnswerEntity> findAll() {
        return repository.findAll();
    }
}
