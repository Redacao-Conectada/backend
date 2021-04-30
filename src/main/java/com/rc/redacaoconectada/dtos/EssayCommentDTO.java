package com.rc.redacaoconectada.dtos;

import com.rc.redacaoconectada.entities.Comment;
import com.rc.redacaoconectada.entities.User;

import java.io.Serializable;

public class EssayCommentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String body;

    private int upVote;

    private UserDTO userDTO;

    private  EssayDTO essayDTO;

    public EssayCommentDTO(Comment comment) {
        this.id = comment.getId();
        this.userDTO = new UserDTO(comment.getUser());
        this.upVote = comment.getUpVote();
        this.essayDTO = new EssayDTO(comment.getEssay());
    }

}
