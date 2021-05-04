package com.rc.redacaoconectada.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_changeRoleRequest")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChangeRoleRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "user_id")
    private Long id;

    private String school_registration;

    private String school_name_as_teacher;

    private String proof_img;

    @ManyToOne
    private User user;
}
