package com.rc.redacaoconectada.factory;

import com.rc.redacaoconectada.entities.Correction;
import com.rc.redacaoconectada.entities.Essay;
import com.rc.redacaoconectada.entities.User;

public class CorrectionFactory {

    public static Correction createCorrection(){
        Essay essay = EssayFactory.createEssay();
        User user = UserFactory.createUserTeacher();

        return new Correction();
    }
}
