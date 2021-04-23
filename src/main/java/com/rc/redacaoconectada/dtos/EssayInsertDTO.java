package com.rc.redacaoconectada.dtos;

import com.rc.redacaoconectada.entities.Essay;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class EssayInsertDTO implements Serializable {

    @NotNull(message = "IdUser requerido")
    private Long idUser;

    @NotNull(message = "UpVote requerido")
    private Integer upVote;

    @NotBlank(message = "Body requerido")
    private String body;

    public EssayInsertDTO(Essay essay) {
        this.idUser = essay.getUser().getId();
        this.upVote = essay.getUpVote();
        this.body = essay.getBody();
    }

}
