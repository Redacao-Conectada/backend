package com.rc.redacaoconectada.dtos;

import com.rc.redacaoconectada.entities.Essay;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class EssayDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    private Long userId;

    private Integer upVote;

    private String body;

    private Boolean isAnon;

    public EssayDTO(Essay essay) {
        this.id = essay.getId();
        this.userId = essay.getUser().getId();
        this.upVote = essay.getUpVote();
        this.body = essay.getBody();
        this.isAnon = essay.getIsAnon();
    }

}
