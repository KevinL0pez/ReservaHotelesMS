package org.reservahoteles.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reservahoteles.dto.*;
import org.reservahoteles.jpa.entities.ReservationEntity;
import org.reservahoteles.service.IReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
@Log4j2
public class ReservationController {

    private final IReservationService iReservationService;

    @GetMapping("/getReservations/all")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ResponseDto<List<ReservationResponseDto>>> getAllReservationsRest() {
        List<ReservationResponseDto> reservations = iReservationService.getAllReservations();
        ResponseDto<List<ReservationResponseDto>> responseDto = new ResponseDto<>();
        if (reservations == null || reservations.isEmpty()) {
            responseDto.setMessage("Reservations not found");
            responseDto.setStatusCode(HttpStatus.NOT_FOUND);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        } else {

            responseDto.setMessage("Reservations found successfully");
            responseDto.setStatusCode(HttpStatus.OK);
            System.out.print(reservations);
            responseDto.setError(false);
            responseDto.setData(reservations);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
    }

    @GetMapping("/getReservations/statusId")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ResponseDto<List<ReservationResponseDto>>> getReservationsByStatusId(@RequestParam Long idStatusReservation) {
        List<ReservationResponseDto> reservations = iReservationService.getFilteredReservationsByStatusId(idStatusReservation);
        ResponseDto<List<ReservationResponseDto>> responseDto = new ResponseDto<>();
        if (reservations.isEmpty()) {
            responseDto.setMessage("Reservations not found");
            responseDto.setStatusCode(HttpStatus.NOT_FOUND);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        } else {
            responseDto.setMessage("Reservations found successfully");
            responseDto.setStatusCode(HttpStatus.OK);
            responseDto.setData(reservations);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
    }

    @GetMapping("/getReservations/userId")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ResponseDto<List<ReservationResponseDto>>> getReservationByUserId(@RequestParam Long idUser) {
        List<ReservationResponseDto> reservations = iReservationService.getReservationsByUserId(idUser);
        ResponseDto<List<ReservationResponseDto>> responseDto = new ResponseDto<>();
        if (reservations.isEmpty()) {
            responseDto.setMessage("Reservations not found");
            responseDto.setStatusCode(HttpStatus.NOT_FOUND);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        } else {
            responseDto.setMessage("Reservations found successfully");
            responseDto.setStatusCode(HttpStatus.OK);
            responseDto.setData(reservations);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
    }

    @GetMapping("/getReservations/userId&statusId")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ResponseDto<List<ReservationResponseDto>>> getReservationsByUserIdAndStatusId(@RequestParam Long idUser, @RequestParam Long idStatusReservation) {
        List<ReservationResponseDto> reservations = iReservationService.getFilteredReservationsByUserIdAndStatusId(idUser, idStatusReservation);
        ResponseDto<List<ReservationResponseDto>> responseDto = new ResponseDto<>();
        if (reservations.isEmpty()) {
            responseDto.setMessage("Reservations not found");
            responseDto.setStatusCode(HttpStatus.NOT_FOUND);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        } else {
            responseDto.setMessage("Reservations found successfully");
            responseDto.setStatusCode(HttpStatus.OK);
            responseDto.setData(reservations);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
    }

    @CrossOrigin("*")
    @PostMapping("/createReservation")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ResponseDto<ReservationRequestDto>> createReservation(@Valid @RequestBody ReservationRequestDto reservationRequestDto, BindingResult bindingResult) {

        ResponseDto<ReservationRequestDto> response = new ResponseDto<>();

        // Si hay errores de validación, devolver mensajes de error
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            bindingResult.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            response.setMessage(errors.toString());
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            response.setError(true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response = iReservationService.createReservation(reservationRequestDto);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }
}
