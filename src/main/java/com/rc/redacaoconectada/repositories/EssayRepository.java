package com.rc.redacaoconectada.repositories;

import com.rc.redacaoconectada.entities.Essay;
import com.rc.redacaoconectada.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface EssayRepository extends JpaRepository<Essay, Long> {

  @Query("SELECT obj FROM Essay obj WHERE obj.user = :user")
  List<Essay> find(User user);
}
