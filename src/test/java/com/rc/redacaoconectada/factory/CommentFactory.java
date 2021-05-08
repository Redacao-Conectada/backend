package com.rc.redacaoconectada.factory;

import com.rc.redacaoconectada.entities.Comment;
import com.rc.redacaoconectada.entities.Essay;
import com.rc.redacaoconectada.entities.User;

import java.time.Instant;

public class CommentFactory {

    public static Comment createComment() {
        User user = UserFactory.createUser();
        Essay essay = EssayFactory.createEssay();
        return new Comment(5L, user, 777, essay, "ATENTAR AOS ERROS DE PORTUGUES");
    }
}