package com.rc.redacaoconectada.dtos;

import com.rc.redacaoconectada.entities.Competence;
import com.rc.redacaoconectada.entities.Correction;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
public class CorrectionDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    private UserDTO teacherDTO;

    private EssayDTO essayDTO;

    private Instant createdDate;

    private Competence competences;

    private Integer correctionGrade;

    public CorrectionDTO(Correction correction) {
        this.id = correction.getId();
        this.createdDate = correction.getCreated_date();
        this.teacherDTO = new UserDTO(correction.getTeacher());
        this.essayDTO = new EssayDTO(correction.getEssay());
        this.competences = correction.getCompetences();
        this.correctionGrade = correction.correctionGrade();
    }
}
