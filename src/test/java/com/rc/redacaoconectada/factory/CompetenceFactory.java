package com.rc.redacaoconectada.factory;

import com.rc.redacaoconectada.entities.Competence;

public class CompetenceFactory {

    public static Competence createCompetence(){
        return  new Competence("Competencia 1 - Excelente", 200 ,"Competencia 2 - Excelente", 200 ,
                "Competencia 3 - Excelente", 200 ,"Competencia 4 - Excelente", 200 ,"Competencia 5 - Excelente", 200);
    }
}
