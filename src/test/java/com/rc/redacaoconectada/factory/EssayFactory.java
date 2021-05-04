package com.rc.redacaoconectada.factory;

import com.rc.redacaoconectada.entities.Essay;
import com.rc.redacaoconectada.entities.User;

import java.time.Instant;

public class EssayFactory {

    public static Essay createEssay() {
        User user = new User(3L, "123.456.789.11", "Bob", "bob@gmail.com", "12345678", Instant.now(),
                "Superior Completo", "UFCG", "PB", "Patos");
        return new Essay(2L, user, 0, "Redação é o processo de redigir (escrever) um texto.");
    }

}
