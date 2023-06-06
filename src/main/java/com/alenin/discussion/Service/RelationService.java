package com.alenin.discussion.Service;

import com.alenin.discussion.Entity.RelationEntity;
import com.alenin.discussion.Repository.RelationRepository;
import org.springframework.stereotype.Service;

@Service
public class RelationService {

    private final RelationRepository repository;

    public RelationService(RelationRepository repository) {
        this.repository = repository;
    }

    public RelationEntity add(RelationEntity relation){
        return repository.save(relation);
    }

    public RelationEntity update(RelationEntity relation){
        return repository.save(relation);
    }

    public void delete(RelationEntity relation){
        repository.delete(relation);
    }
}
