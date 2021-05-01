package com.rc.redacaoconectada.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tb_changeRoleRequest")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChangeRoleRequest {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String school_registration;

    private String school_name_as_teacher;

    private String proof_img;

    @ManyToOne
    private User user;
}
