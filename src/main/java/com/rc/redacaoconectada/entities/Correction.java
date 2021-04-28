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
            @AttributeOverride(name = "competenceGrade1", column = @Column(name = "first_competence_grade")),
            @AttributeOverride(name = "grade1", column = @Column(name = "first_grade")),
            @AttributeOverride(name = "competenceGrade2", column = @Column(name = "second_competence_grade")),
            @AttributeOverride(name = "grade2", column = @Column(name = "second_grade")),
            @AttributeOverride(name = "competenceGrade3", column = @Column(name = "third_competence_grade")),
            @AttributeOverride(name = "grade3", column = @Column(name = "third_grade")),
            @AttributeOverride(name = "competenceGrade4", column = @Column(name = "fourth_competence_grade")),
            @AttributeOverride(name = "grade4", column = @Column(name = "fourth_grade")),
            @AttributeOverride(name = "competenceGrade5", column = @Column(name = "fifth_competence_grade")),
            @AttributeOverride(name = "grade5", column = @Column(name = "fifth_grade"))
    })
    private Competence competences;

    public Integer correctionGrade(){
        return competences.getGradesSum();
    }
}
