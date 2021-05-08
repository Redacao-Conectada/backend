package com.rc.redacaoconectada.factory;

import com.rc.redacaoconectada.entities.Role;

public class RoleFactory {

    public static Role createRoleStudent(){
        return new Role(1L, "ROLE_STUDENT");
    }

    public static Role createRoleTeacher(){
        return new Role(2L, "ROLE_TEACHER");
    }

    public static Role createRoleAdmin(){
        return new Role(3L, "ROLE_ADMIN");
    }
}
