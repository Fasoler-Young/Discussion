package com.alenin.discussion.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "answer", schema = "public", catalog = "discussion")
public class AnswerEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "thesis", nullable = false)
    private Integer thesis;
    @Basic
    @Column(name = "confidence", nullable = false)
    private Float confidence;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerEntity that = (AnswerEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(thesis, that.thesis)) return false;
        return Objects.equals(confidence, that.confidence);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (thesis != null ? thesis.hashCode() : 0);
        result = 31 * result + (confidence != null ? confidence.hashCode() : 0);
        return result;
    }
}
