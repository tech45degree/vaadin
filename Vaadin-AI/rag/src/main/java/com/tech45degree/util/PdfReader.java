package com.tech45degree.util;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PdfReader {

    /**
     * Reads the PDF (e.g., "classpath:/docs/manual.pdf") and returns
     * documents
     */

    public List<Document> readPdf(String pdfResourceLocation) {
        var pdfResource = new ClassPathResource(pdfResourceLocation);
        PagePdfDocumentReader reader = new PagePdfDocumentReader(
                pdfResource,
                PdfDocumentReaderConfig.builder().build()
        );
        return reader.read();
    }
}