package com.unisp.gestioneunisp.repository;

import com.unisp.gestioneunisp.model.Attivita;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface AttivitaRepository extends JpaRepository<Attivita, Long> {
    List<Attivita> findByDataOraBetweenAndIsDeletedFalse(LocalDateTime start, LocalDateTime end);
    List<Attivita> findByIsDeletedFalseOrderByDataOraDesc();
}