package com.rc.redacaoconectada.dtos;

import com.rc.redacaoconectada.entities.Essay;
import com.rc.redacaoconectada.services.validation.UserInsertValid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class EssayDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer upVote;
    private String body;

    public EssayDTO(Essay essay) {
        this.id = essay.getId();
        this.upVote = essay.getUpVote();
        this.body = essay.getBody();
    }

}
