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

    @NotBlank(message = "Body requerido")
    private String body;

    public EssayInsertDTO(Essay essay) {
        this.idUser = essay.getUser().getId();
        this.body = essay.getBody();
    }

}
