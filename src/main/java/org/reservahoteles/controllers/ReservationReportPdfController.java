package org.reservahoteles.controllers;

import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reservahoteles.dto.ReservationResponseDto;
import org.reservahoteles.jpa.entities.ReservationEntity;
import org.reservahoteles.service.IReservationService;
import org.reservahoteles.utilities.PDFUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservationsReportPdf")
@Log4j2
public class ReservationReportPdfController {

    @Autowired
    private final IReservationService iReservationService;

    @PostMapping("/generateReportPdf")
    public ResponseEntity<byte[]> exportPdf(@RequestBody Map request) throws DocumentException {
        if (request == null || request.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<ReservationResponseDto> reservations = null;
        if (request.containsKey("idHotel")) {
            reservations = iReservationService.getFilteredReservationsByHotelId(Long.parseLong(request.get("idHotel").toString()));


        } else if (request.containsKey("idUser")) {
            reservations = iReservationService.getReservationsByUserId(Long.parseLong(request.get("idUser").toString()));

        } else if (request.containsKey("checkInDatetime") && request.containsKey("checkOutDateTime")) {
            reservations = iReservationService.getReservationsByCheckinAndCheckoutDates(LocalDateTime.parse(
                    request.get("checkInDatetime").toString()),
                    LocalDateTime.parse(request.get("checkOutDateTime").toString())
            );
        }

        ByteArrayOutputStream pdfStream = PDFUtil.generatePdfStream(reservations);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=query_results.pdf");
        headers.setContentLength(pdfStream.size());
        return new ResponseEntity<>(pdfStream.toByteArray(), headers, HttpStatus.OK);

    }
}
