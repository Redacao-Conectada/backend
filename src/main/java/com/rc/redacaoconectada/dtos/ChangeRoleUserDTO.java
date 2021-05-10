package com.rc.redacaoconectada.dtos;

import com.rc.redacaoconectada.entities.ChangeRoleRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class ChangeRoleUserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "school_registration requerido")
    private String school_registration;

    @NotBlank(message = "school_name_as_teacher requerido")
    private String school_name_as_teacher;

    @NotBlank(message = "proof_img requerido")
    private String proof_img;

    private UserDTO user;

    public ChangeRoleUserDTO(ChangeRoleRequest changeRoleRequest) {
        this.id = changeRoleRequest.getId();
        this.school_registration = changeRoleRequest.getSchool_registration();
        this.school_name_as_teacher = changeRoleRequest.getSchool_name_as_teacher();
        this.proof_img = changeRoleRequest.getProof_img();
        this.user = new UserDTO(changeRoleRequest.getUser());
    }
}
