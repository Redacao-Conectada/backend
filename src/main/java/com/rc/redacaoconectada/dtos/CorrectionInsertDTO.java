package com.rc.redacaoconectada.dtos;

import com.rc.redacaoconectada.entities.Competence;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.Instant;

@Data
@NoArgsConstructor
public class CorrectionInsertDTO {

    @NotNull(message = "IdTeacherUser requerido")
    private Long idTeacherUser;

    @NotNull(message = "IdEssay requerido")
    private Long idEssay;

    @Past(message = "A data de correção não pode ser futura")
    private Instant createdDate;

    private Competence competences;

    public CorrectionInsertDTO(CorrectionInsertDTO correctionInsertDTO){
        this.idTeacherUser = correctionInsertDTO.getIdTeacherUser();
        this.idEssay = correctionInsertDTO.getIdEssay();
        this.createdDate = correctionInsertDTO.getCreatedDate();
        this.competences = correctionInsertDTO.getCompetences();
    }
}
