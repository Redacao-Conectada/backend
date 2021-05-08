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

    private Long author;

    private Integer upVote;

    private String body;

    private Boolean isAnon;

    private Long correctionId;

    private Integer grade;

    private String authorImage;

    private String authorName;


    public EssayDTO(Essay essay) {
        this.id = essay.getId();
        this.author = essay.getUser().getId();
        this.upVote = essay.getUpVote();
        this.body = essay.getBody();
        this.isAnon = essay.getIsAnon();
        this.correctionId = essay.getCorrection().getId();
        this.grade = essay.getCorrection().correctionGrade();
        this.authorImage = essay.getUser().getImage();
        this.authorName = essay.getUser().getName();
    }

}
