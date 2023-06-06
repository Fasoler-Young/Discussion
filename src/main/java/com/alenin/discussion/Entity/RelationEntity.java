package com.alenin.discussion.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "relation", schema = "public", catalog = "discussion")
public class RelationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "thesis", nullable = false)
    private Integer thesis;
    @Basic
    @Column(name = "argument", nullable = false)
    private Integer argument;
    @Basic
    @Column(name = "influence", nullable = false)
    private Float influence;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RelationEntity that = (RelationEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(thesis, that.thesis)) return false;
        if (!Objects.equals(argument, that.argument)) return false;
        return Objects.equals(influence, that.influence);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (thesis != null ? thesis.hashCode() : 0);
        result = 31 * result + (argument != null ? argument.hashCode() : 0);
        result = 31 * result + (influence != null ? influence.hashCode() : 0);
        return result;
    }
}
