package com.alenin.discussion.Service;

import com.alenin.discussion.DTO.RelationDTO;
import com.alenin.discussion.Entity.RelationEntity;
import com.alenin.discussion.Repository.RelationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RelationService {

    private final RelationRepository repository;

    public RelationService(RelationRepository repository) {
        this.repository = repository;
    }

    public RelationEntity add(RelationDTO relation){
        return repository.save(relation.toEntity());
    }

    public RelationEntity update(RelationEntity relation){
        return repository.save(relation);
    }

    public void delete(RelationEntity relation){
        repository.delete(relation);
    }

    public RelationEntity findById(Integer id) {
        Optional<RelationEntity> relation = repository.findById(id);
        return relation.orElse(null);
    }

    public Iterable<RelationEntity> findAll() {
        return repository.findAll();
    }
}
