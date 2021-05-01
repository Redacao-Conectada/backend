package com.rc.redacaoconectada.repositories;

import com.rc.redacaoconectada.entities.Essay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EssayRepository extends JpaRepository<Essay, Long> {

//    List<Essay> findBy

}
