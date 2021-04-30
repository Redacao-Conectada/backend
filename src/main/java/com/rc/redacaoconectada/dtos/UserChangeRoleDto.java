package com.rc.redacaoconectada.dtos;

import com.rc.redacaoconectada.entities.ChangeRoleRequest;
import com.rc.redacaoconectada.entities.User;

import java.io.Serializable;

public class UserChangeRoleDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String schoolRegistration;
    private String schoolNameAsTeacher;
    private String proofImg;
    private User user;

    public UserChangeRoleDto(ChangeRoleRequest changeRoleRequest) {
        this.id = changeRoleRequest.getId();
        this.proofImg = changeRoleRequest.getProof_img();
        this.schoolNameAsTeacher = changeRoleRequest.getSchool_name_as_teacher();
        this.schoolRegistration = changeRoleRequest.getSchool_registration();
        this.user = changeRoleRequest.getUser();
    }
}
