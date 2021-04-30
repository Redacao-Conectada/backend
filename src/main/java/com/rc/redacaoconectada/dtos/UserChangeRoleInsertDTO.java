package com.rc.redacaoconectada.dtos;

import com.rc.redacaoconectada.services.validation.UserInsertValid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import com.rc.redacaoconectada.entities.ChangeRoleRequest;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@UserInsertValid
public class UserChangeRoleInsertDTO implements Serializable {

    @NotNull(message = "school_registration requerido")
    private String school_registration;

    @NotNull(message = "school_name_as_teacher requerido")
    private String school_name_as_teacher;

    @NotNull(message = "proof_img requerido")
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
