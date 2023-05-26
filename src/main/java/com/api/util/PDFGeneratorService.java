package com.api.util;

import java.awt.Color;

// import java.io.IOException;

import org.springframework.stereotype.Service;

import com.api.exception.member.MemberExceptionBadRequest;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class PDFGeneratorService {
    public void export(HttpServletResponse response) throws MemberExceptionBadRequest {
        try {
            Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();
            Font title = FontFactory.getFont(FontFactory.COURIER_BOLD);
            title.setColor(Color.DARK_GRAY);

            Paragraph paragraph = new Paragraph("Gofit Card Member", title);
            paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
            document.add(paragraph);

            // Image icon =

            // DateFormat dateFormat = new SimpleDateFormat("EE, dd/MM/yyyy HH:mm:ss");
            // Date date = new Date();
            // String currentDateTime = dateFormat.format(date);

            Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
            fontParagraph.setSize(18);

            Paragraph paragraph2 = new Paragraph("This is a paragraph", fontParagraph);
            paragraph2.setAlignment(Paragraph.ALIGN_CENTER);

            document.add(paragraph2);
            document.close();
        } catch (Exception e) {
            throw new MemberExceptionBadRequest("Error Export PDF" + response, e);
        }

    }
}
