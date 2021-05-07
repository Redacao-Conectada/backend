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
    private String competence1Comments;

    @NotNull
    private Integer competence1Grade;

    @NotNull
    private String competence2Comments;

    @NotNull
    private Integer competence2Grade;

    @NotNull
    private String competence3Comments;

    @NotNull
    private Integer competence3Grade;

    @NotNull
    private String competence4Comments;

    @NotNull
    private Integer competence4Grade;

    @NotNull
    private String competence5Comments;

    @NotNull
    private Integer competence5Grade;

    public Integer getGradesSum(){
        return this.competence1Grade + this.competence2Grade + this.competence3Grade + this.competence4Grade + this.competence5Grade;
    }
}
