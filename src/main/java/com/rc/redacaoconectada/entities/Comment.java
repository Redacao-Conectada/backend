package com.rc.redacaoconectada.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tb_Comment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Integer upVote;

    @ManyToOne
    @JoinColumn(name = "essay_id")
    private Essay essay;

    @Column(columnDefinition = "TEXT")
    private String body;

}
