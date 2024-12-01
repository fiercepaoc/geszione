package com.unisp.gestioneunisp.repository;

import com.unisp.gestioneunisp.model.Documenti;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DocumentiRepository extends JpaRepository<Documenti, Long> {
    List<Documenti> findByMembroIdAndIsDeletedFalse(Long membroId);
    List<Documenti> findByTipoAndIsDeletedFalse(String tipo);
}