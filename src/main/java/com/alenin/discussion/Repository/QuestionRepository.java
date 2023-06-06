package com.alenin.discussion.Repository;

import com.alenin.discussion.Entity.QuestionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<QuestionEntity, Integer> {
}
