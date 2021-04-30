package com.rc.redacaoconectada.dtos;

import com.rc.redacaoconectada.entities.Essay;
import com.rc.redacaoconectada.entities.User;
import com.rc.redacaoconectada.entities.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class EssayCommentInsertDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "IdUser requerido")
    private Long idUser;

    @NotNull(message = "IdEssay requerido")
    private Long idEssay;

    @NotBlank(message = "Body requerido")
    private String body;

    public EssayCommentInsertDTO(Comment comment) {
        this.idUser = comment.getUser().getId();
        this.idEssay = comment.getEssay().getId();
        this.body = comment.getBody();
    }
}
