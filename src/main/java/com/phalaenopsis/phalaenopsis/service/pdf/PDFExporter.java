package com.phalaenopsis.phalaenopsis.service.pdf;

import java.awt.Color;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.LineSeparator;
import com.phalaenopsis.phalaenopsis.domain.User;


public class PDFExporter {
    private User user;
    public PDFExporter(User user) {
        this.user = user;
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, StandardCharsets.UTF_8.name());
        font.setSize(30);
        font.setColor(Color.BLUE);

        Font font2 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, StandardCharsets.UTF_8.name());
        font2.setSize(20);



        Paragraph p = new Paragraph("Certificate for completed training awarded to: " + user.getFullName(), font);

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormatter.format(new Date());
        Paragraph newline = new Paragraph("\n");
        Paragraph p2 = new Paragraph("For the completion of the course about growing Phalaenopsis orchids on day " + currentDate + ".", font2);
        Paragraph certificateId = new Paragraph("Certificate id: " + user.certificate.id);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        p2.setAlignment(Paragraph.ALIGN_CENTER);
        certificateId.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        document.add(newline);
        document.add(newline);
        document.add(newline);
        document.add(newline);
        document.add(newline);
        document.add(newline);
        document.add(p2);
        document.add(newline);
        document.add(newline);
        document.add(newline);
        document.add(certificateId);



        document.close();

    }
}