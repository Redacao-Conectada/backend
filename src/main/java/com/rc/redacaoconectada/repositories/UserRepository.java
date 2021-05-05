package com.rc.redacaoconectada.repositories;

import com.rc.redacaoconectada.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);
}
