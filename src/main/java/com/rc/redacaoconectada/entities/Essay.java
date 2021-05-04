package com.rc.redacaoconectada.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "essay")
    private final Set<Comment> comments = new HashSet<>();

    private Instant createdAt;

    @PrePersist
    public void prePersistent() {
        this.createdAt = Instant.now();
    }

    @Override
    public String toString() {
        return "Essay{" +
                "id=" + id +
                ", user=" + user +
                ", upVote=" + upVote +
                ", body='" + body + '\'' +
                ", comments=" + comments +
                ", createdAt=" + createdAt +
                '}';
    }
}
