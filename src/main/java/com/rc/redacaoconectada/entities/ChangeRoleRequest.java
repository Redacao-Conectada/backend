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
    private Long id;

    private String school_registration;

    private String school_name_as_teacher;

    private String proof_img;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public ChangeRoleRequest(ChangeRoleRequest changeRoleRequest) {
        this.id = changeRoleRequest.getId();
        this.school_registration = changeRoleRequest.getSchool_registration();
        this.school_name_as_teacher = changeRoleRequest.getSchool_name_as_teacher();
        this.proof_img = changeRoleRequest.getProof_img();

    }
}
