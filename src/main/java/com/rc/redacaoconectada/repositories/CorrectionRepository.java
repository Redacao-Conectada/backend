package com.rc.redacaoconectada.repositories;

import com.rc.redacaoconectada.entities.Correction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrectionRepository extends JpaRepository<Correction, Long> {
}
