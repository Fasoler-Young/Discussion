package com.alenin.discussion.DTO;

import com.alenin.discussion.Entity.AnswerEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AnswerDTO {
    @NotNull
    private Integer thesis;

    @NotNull
    private Float confidence;

    public AnswerEntity toEntity(){
        AnswerEntity answer = new AnswerEntity();
        answer.setThesis(this.getThesis());
        answer.setConfidence(this.getConfidence());
        return answer;
    }
}
