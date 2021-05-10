package com.rc.redacaoconectada.repositories;

import com.rc.redacaoconectada.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT obj FROM Role obj WHERE obj.authority = :role_teacher")
    Role findByAuthority(String role_teacher);
}
