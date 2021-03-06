package com.rc.redacaoconectada.dtos;

import com.rc.redacaoconectada.entities.Comment;
import com.rc.redacaoconectada.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class EssayCommentDTO implements Serializable, Comparable<EssayCommentDTO> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String body;

    private int upVote;

    private final Map<String, Object> userInfo = new HashMap<>();

    private Long essayId;

    public EssayCommentDTO(Comment comment) {
        this.id = comment.getId();
        mapUser(comment.getUser());
        this.upVote = comment.getUpVote();
        this.essayId = comment.getEssay().getId();
        this.body = comment.getBody();
    }

    private void mapUser(User user) {
        userInfo.put("id", user.getId());
        userInfo.put("name", user.getName());
        userInfo.put("image", user.getImage());
    }

    @Override
    public int compareTo(EssayCommentDTO o) {
        return this.getId().compareTo(o.getId());
    }
}
