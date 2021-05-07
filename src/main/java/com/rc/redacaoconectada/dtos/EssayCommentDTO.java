package com.rc.redacaoconectada.dtos;

import com.rc.redacaoconectada.entities.Comment;
import com.rc.redacaoconectada.entities.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class EssayCommentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String body;

    private int upVote;

    private Long author;

    private  Long essayId;

    public EssayCommentDTO(Comment comment) {
        this.id = comment.getId();
        this.author = comment.getUser().getId();
        this.upVote = comment.getUpVote();
        this.essayId = comment.getEssay().getId();
        this.body = comment.getBody();
    }

}
