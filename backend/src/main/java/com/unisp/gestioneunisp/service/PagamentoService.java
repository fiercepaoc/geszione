package com.unisp.gestione.services;

import com.unisp.gestione.models.Membro;
import com.unisp.gestione.models.Pagamento;
import com.unisp.gestione.repositories.PagamentoRepository;
import com.unisp.gestione.utils.PDFGenerator;
import com.unisp.gestione.utils.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;
    private final PDFGenerator pdfGenerator;
    private final EmailSender emailSender;

    @Transactional
    public Pagamento processaPagamentoIscrizione(Membro membro, String transazioneId) {
        Pagamento pagamento = new Pagamento();
        pagamento.setMembro(membro);
        pagamento.setTipoPagamento("iscrizione");
        pagamento.setImporto(new BigDecimal("5.00"));
        pagamento.setDataPagamento(LocalDateTime.now());
        pagamento.setTransazioneId(transazioneId);
        
        Pagamento saved = pagamentoRepository.save(pagamento);
        
        // Genera ricevuta PDF
        byte[] ricevutaPdf = pdfGenerator.generaRicevutaPagamento(saved);
        
        // Invia email con ricevuta
        emailSender.inviaRicevutaPagamento(membro.getEmail(), ricevutaPdf);
        
        return saved;
    }

    @Transactional
    public Pagamento processaDonazione(Membro membro, BigDecimal importo, String transazioneId) {
        Pagamento pagamento = new Pagamento();
        pagamento.setMembro(membro);
        pagamento.setTipoPagamento("donazione");
        pagamento.setImporto(importo);
        pagamento.setDataPagamento(LocalDateTime.now());
        pagamento.setTransazioneId(transazioneId);
        
        return pagamentoRepository.save(pagamento);
    }

    public List<Pagamento> getPagamentiMembro(Long membroId) {
        return pagamentoRepository.findByMembroIdAndIsDeletedFalseOrderByDataPagamentoDesc(membroId);
    }

    public boolean verificaPagamentoIscrizione(Long membroId, int anno) {
        return pagamentoRepository.existsByMembroIdAndTipoPagamentoAndAnno(membroId, "iscrizione", anno);
    }
}
