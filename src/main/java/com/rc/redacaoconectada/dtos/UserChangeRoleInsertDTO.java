package com.rc.redacaoconectada.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.rc.redacaoconectada.entities.ChangeRoleRequest;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class UserChangeRoleInsertDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "school_registration requerido")
    private String school_registration;

    @NotBlank(message = "school_name_as_teacher requerido")
    private String school_name_as_teacher;

    @NotBlank(message = "proof_img requerido")
    private String proof_img;

    @NotNull(message = "IdUser requerido")
    private Long idUser;

    public UserChangeRoleInsertDTO(ChangeRoleRequest userChangeRole) {
        this.school_registration = userChangeRole.getSchool_registration();
        this.proof_img = userChangeRole.getProof_img();
        this.school_name_as_teacher = userChangeRole.getSchool_name_as_teacher();
        this.idUser = userChangeRole.getUser().getId();
    }

}
