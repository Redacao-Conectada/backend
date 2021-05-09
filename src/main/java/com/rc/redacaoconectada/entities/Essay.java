package com.rc.redacaoconectada.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_essay")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Essay implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "correction_id")
    private Correction correction;

    private Integer upVote;

    @Column(columnDefinition = "TEXT")
    private String body;

    @OneToMany(mappedBy = "essay", fetch = FetchType.EAGER)
    private final Set<Comment> comments = new HashSet<>();

    private Instant createdAt;
  
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_essay_user_upvote",
               joinColumns = @JoinColumn(name = "essay_id"),
               inverseJoinColumns = @JoinColumn(name = "user_id"))
    private final List<User> userUpVotes = new ArrayList<>();

    private Boolean isAnon;
  
    @PrePersist
    public void prePersistent() {
        this.createdAt = Instant.now();
    }

    public void setUserUpVotes(User user) {
        userUpVotes.add(user);
    }

    public void removeUserUpVotes (User user) {
        userUpVotes.remove(user);
    }

    public Integer getQuantityUpVotes(){
        return userUpVotes.size();
    }

    public Integer getQuantityComments(){
        return comments.size();
    }
}
