package com.alenin.discussion.DTO;

import com.alenin.discussion.Entity.QuestionEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class QuestionDTO {

    @NotNull
    @Size(min = 7, max = 30, message = "The question must contain from 7 to 30 characters")
    private String title;

    @Size(max = 300, message = "The comment must contain up to 300 characters")
    private String comment;

    public QuestionEntity toEntity(){
        QuestionEntity question = new QuestionEntity();
        question.setTitle(this.getTitle());
        question.setComment(this.getComment());
        return question;
    }
}

