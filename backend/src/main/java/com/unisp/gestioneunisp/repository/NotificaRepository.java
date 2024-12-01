package com.unisp.gestione.repositories;

import com.unisp.gestione.models.Notifica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface NotificaRepository extends JpaRepository<Notifica, Long> {
    List<Notifica> findByMembroIdAndIsDeletedFalseOrderByDataInvioDesc(Long membroId);
    
    @Query("SELECT n FROM Notifica n WHERE n.membro.id = :membroId AND n.letto = false AND n.isDeleted = false")
    List<Notifica> findUnreadByMembro(@Param("membroId") Long membroId);
    
    long countByMembroIdAndLettoFalseAndIsDeletedFalse(Long membroId);
}
