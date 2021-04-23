package com.rc.redacaoconectada.dtos;

import com.rc.redacaoconectada.entities.Essay;
import com.rc.redacaoconectada.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class EssayInsertDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    private Long idUser;

    private Integer upVote;

    private String body;

    public EssayInsertDTO(Essay essay) {
        this.id = essay.getId();
        this.idUser = essay.getUser().getId();
        this.upVote = essay.getUpVote();
        this.body = essay.getBody();
    }

}
