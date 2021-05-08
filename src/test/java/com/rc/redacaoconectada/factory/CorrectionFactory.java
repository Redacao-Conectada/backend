package com.rc.redacaoconectada.factory;

import com.rc.redacaoconectada.entities.Competence;
import com.rc.redacaoconectada.entities.Correction;
import com.rc.redacaoconectada.entities.Essay;
import com.rc.redacaoconectada.entities.User;

import java.time.Instant;

public class CorrectionFactory {

    public static Correction createCorrection(){
        Essay essay = EssayFactory.createEssay();
        User user = UserFactory.createUserTeacher();
        Competence competence = CompetenceFactory.createCompetence();
        return new Correction(1L, Instant.now(), user, essay, competence);
    }
}
