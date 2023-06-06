package com.alenin.discussion.Repository;

import com.alenin.discussion.Entity.AnswerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends CrudRepository<AnswerEntity,Integer> {

}
