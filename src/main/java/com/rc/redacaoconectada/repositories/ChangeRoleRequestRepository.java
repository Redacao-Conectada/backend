package com.rc.redacaoconectada.repositories;

import com.rc.redacaoconectada.entities.ChangeRoleRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeRoleRequestRepository extends JpaRepository<ChangeRoleRequest, Long> {
}
