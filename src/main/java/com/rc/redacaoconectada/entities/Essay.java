package com.rc.redacaoconectada.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_essay")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Essay implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Integer upVote;

    @Column(columnDefinition = "TEXT")
    private String body;

    @ManyToMany(fetch = FetchType.EAGER)
    private final List<User> userUpVotes = new ArrayList<>();

    public void setUserUpVotes(User user) {
        userUpVotes.add(user);
    }

    public void removeUserUpVotes (User user) {
        userUpVotes.remove(user);
    }

}
