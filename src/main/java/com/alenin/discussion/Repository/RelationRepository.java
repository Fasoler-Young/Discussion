package com.alenin.discussion.Repository;

import com.alenin.discussion.Entity.RelationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationRepository extends CrudRepository<RelationEntity, Integer> {
}
