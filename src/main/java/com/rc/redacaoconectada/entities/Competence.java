package com.rc.redacaoconectada.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Competence {

    @NotNull
    private Integer competenceGrade1;

    @NotNull
    private Integer grade1;

    @NotNull
    private Integer competenceGrade2;

    @NotNull
    private Integer grade2;

    @NotNull
    private Integer competenceGrade3;

    @NotNull
    private Integer grade3;

    @NotNull
    private Integer competenceGrade4;

    @NotNull
    private Integer grade4;

    @NotNull
    private Integer competenceGrade5;

    @NotNull
    private Integer grade5;

    public Integer getGradesSum(){
        return this.grade1 + this.grade2 + this.grade3 + this.grade4 + this.grade5;
    }
}
