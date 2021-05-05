package com.rc.redacaoconectada.dtos;

import com.rc.redacaoconectada.entities.ChangeRoleRequest;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserChangeRoleDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String schoolRegistration;
    private String schoolNameAsTeacher;
    private String proofImg;
    private UserDTO userDTO;

    public UserChangeRoleDTO(ChangeRoleRequest changeRoleRequest) {
        this.id = changeRoleRequest.getId();
        this.proofImg = changeRoleRequest.getProof_img();
        this.schoolNameAsTeacher = changeRoleRequest.getSchool_name_as_teacher();
        this.schoolRegistration = changeRoleRequest.getSchool_registration();
        this.userDTO = new UserDTO(changeRoleRequest.getUser());
    }
}
