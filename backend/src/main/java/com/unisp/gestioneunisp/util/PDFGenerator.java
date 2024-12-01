package com.unisp.gestione.utils;

import com.unisp.gestione.models.Pagamento;
import org.springframework.stereotype.Component;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;

@Component
public class PDFGenerator {

    public byte[] generaRicevutaPagamento(Pagamento pagamento) {
        try {
            Document document = new Document();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, out);

            document.open();
            
            // Aggiungi intestazione
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Ricevuta di Pagamento", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            
            // Dettagli pagamento
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Numero Transazione: " + pagamento.getTransazioneId()));
            document.add(new Paragraph("Data: " + pagamento.getDataPagamento()));
            document.add(new Paragraph("Importo: €" + pagamento.getImporto()));
            document.add(new Paragraph("Tipo: " + pagamento.getTipoPagamento()));
            
            document.close();
            return out.toByteArray();
            
        } catch (Exception e) {
            throw new RuntimeException("Errore nella generazione del PDF", e);
        }
    }
}
