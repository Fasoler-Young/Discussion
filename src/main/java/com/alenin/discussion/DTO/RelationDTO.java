package com.alenin.discussion.DTO;

import com.alenin.discussion.Entity.RelationEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RelationDTO {

    @NotNull
    private Integer thesis;

    @NotNull
    private Integer argument;

    @NotNull
    private Float influence;

    public RelationEntity toEntity(){
        RelationEntity relation = new RelationEntity();
        relation.setThesis(this.thesis);
        relation.setArgument(this.argument);
        relation.setInfluence(this.influence);
        return relation;
    }



}
