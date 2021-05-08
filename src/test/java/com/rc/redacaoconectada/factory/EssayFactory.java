package com.rc.redacaoconectada.factory;

import com.rc.redacaoconectada.entities.Essay;
import com.rc.redacaoconectada.entities.User;

import java.time.Instant;

public class EssayFactory {

    public static Essay createEssay() {
        User user = UserFactory.createUserStudent();
        return new Essay(2L, "Titulo: Uma grande redação", user, null,0, "Redação é o processo de redigir (escrever) um texto.", Instant.now(), false);
    }

}
