package com.rc.redacaoconectada.dtos;

import com.rc.redacaoconectada.entities.Essay;
import com.rc.redacaoconectada.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
public class EssayDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    private String title;

    private Long author;

    private Integer upVote;

    private String body;

    private Boolean isAnon;

    private Long correctionId;

    private Integer grade;

    private String authorImage;

    private String authorName;

    private Instant createdAt;

    private Integer totalUpVote;

    private Integer totalComments;

    private boolean hasUserUpVoted;


    public EssayDTO(Essay essay, User user) {
        this.id = essay.getId();
        this.title = essay.getTitle();
        this.author = essay.getUser().getId();
        this.upVote = essay.getUpVote();
        this.body = essay.getBody();
        this.isAnon = essay.getIsAnon();
        this.authorImage = essay.getUser().getImage();
        this.authorName = essay.getUser().getName();
        this.createdAt = essay.getCreatedAt();
        this.totalUpVote = essay.getQuantityUpVotes();
        this.totalComments = essay.getQuantityComments();
        this.hasUserUpVoted = hasUserUpVotedVerify(essay, user);
        correctionVerify(essay);
    }

    private boolean hasUserUpVotedVerify(Essay e, User u) {
        return e.getUserUpVotes().contains(u);
    }

    private void correctionVerify(Essay essay){
        if (essay.getCorrection() != null){
            this.correctionId = essay.getCorrection().getId();
            this.grade = essay.getCorrection().correctionGrade();
        }
        else{
            this.correctionId = null;
            this.grade = null;
        }
    }
}
