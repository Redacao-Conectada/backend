package com.rc.redacaoconectada.dtos;

import com.rc.redacaoconectada.entities.Essay;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
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
