package com.unisp.gestioneunisp.repository;

import com.unisp.gestioneunisp.model.Pagamenti;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PagamentiRepository extends JpaRepository<Pagamenti, Long> {
    List<Pagamenti> findByMembroIdAndIsDeletedFalse(Long membroId);
    boolean existsByTransazioneId(String transazioneId);
}