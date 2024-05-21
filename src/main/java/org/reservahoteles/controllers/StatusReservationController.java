package org.reservahoteles.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reservahoteles.dto.ResponseDto;
import org.reservahoteles.dto.StatusReservationDto;
import org.reservahoteles.service.IStatusReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statusReservation")
@Log4j2
public class StatusReservationController {

    private final IStatusReservationService iStatusReservationService;

    @GetMapping("/getStatusReservations")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ResponseDto<List<StatusReservationDto>>> getStatusReservations() {
        List<StatusReservationDto> statusReservations = iStatusReservationService.getStatusReservations();
        ResponseDto<List<StatusReservationDto>> responseDto = new ResponseDto<>();
        if (statusReservations.isEmpty()) {
            responseDto.setMessage("Status Reservations not found");
            responseDto.setStatusCode(HttpStatus.NOT_FOUND);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        } else {
            responseDto.setMessage("Status Reservations found successfully");
            responseDto.setStatusCode(HttpStatus.OK);
            responseDto.setData(statusReservations);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
    }
}
