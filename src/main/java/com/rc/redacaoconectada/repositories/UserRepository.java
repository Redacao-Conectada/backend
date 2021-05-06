package com.rc.redacaoconectada.repositories;

import com.rc.redacaoconectada.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);

  @Query("SELECT obj FROM User obj WHERE LOWER(obj.name) LIKE LOWER(CONCAT('%', :name, '%')) ")
  Page<User> findUserByName(String name, Pageable pageable);
}
