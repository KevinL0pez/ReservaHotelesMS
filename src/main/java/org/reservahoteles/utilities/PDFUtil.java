package org.reservahoteles.utilities;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.reservahoteles.dto.ReservationResponseDto;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class PDFUtil {
    public static ByteArrayOutputStream generatePdfStream(List<ReservationResponseDto> queryResults) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // Define the font for column headers
        Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

        // Create a table with the same number of columns as the data
        PdfPTable table = new PdfPTable(10); // 10 columns as we have 10 fields

        // Set table width
        table.setWidthPercentage(110);

        // Add column headers
        String[] columns = {"ID Reservation", "Number Document User", "User Name", "Hotel Name", "Room ID", "Price Per Night",
                "Check-In Date", "Check-Out Date", "Total Price", "Status"};

        Paragraph titlePage = new Paragraph("Hotel reservation report");

        titlePage.setAlignment(Element.ALIGN_CENTER);

        document.add(titlePage);

        document.add(Chunk.NEWLINE);


        for (String column : columns) {
            PdfPCell header = new PdfPCell();
            header.setBorderWidth(2);
            header.setPhrase(new Phrase(column, boldFont));
            table.addCell(header);
        }

        // Write data rows
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (ReservationResponseDto row : queryResults) {
            table.addCell(row.getIdReservation().toString());
            table.addCell(row.getUser().getNumberDocumentUser());
            table.addCell(row.getUser().getNamesUser() + " " + row.getUser().getLastNamesUser());
            table.addCell(row.getHotel().getNameHotel());
            table.addCell(row.getHotelRoomEntity().getIdHotelRoom().toString());
            table.addCell(row.getHotelRoomEntity().getPricePerNight().toString());
            table.addCell(row.getCheckInDatetime().format(formatter));
            table.addCell(row.getCheckOutDatetime().format(formatter));
            table.addCell(row.getTotalPrice().toString());
            table.addCell(row.getStatusReservation().getTitleStatusReservation());
        }

        // Add the table to the document
        document.add(table);

        document.close();
        return outputStream;
    }
}

