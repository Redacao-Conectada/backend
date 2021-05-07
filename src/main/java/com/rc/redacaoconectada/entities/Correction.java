package com.rc.redacaoconectada.entities;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "tb_correction")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Correction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant created_date;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @OneToOne
    @JoinColumn(name = "essay_id")
    private Essay essay;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "competence1Comments", column = @Column(name = "first_competence_comments")),
            @AttributeOverride(name = "competence1Grade", column = @Column(name = "first_competence_grade")),
            @AttributeOverride(name = "competence2Comments", column = @Column(name = "second_competence_comments")),
            @AttributeOverride(name = "competence2Grade", column = @Column(name = "second_competence_grade")),
            @AttributeOverride(name = "competence3Comments", column = @Column(name = "third_competence_comments")),
            @AttributeOverride(name = "competence3Grade", column = @Column(name = "third_competence_grade")),
            @AttributeOverride(name = "competence4Comments", column = @Column(name = "fourth_competence_comments")),
            @AttributeOverride(name = "competence4Grade", column = @Column(name = "fourth_competence_grade")),
            @AttributeOverride(name = "competence5Comments", column = @Column(name = "fifth_competence_comments")),
            @AttributeOverride(name = "competence5Grade", column = @Column(name = "fifth_competence_grade"))
    })
    private Competence competences;

    public Integer correctionGrade(){
        return competences.getGradesSum();
    }
}
