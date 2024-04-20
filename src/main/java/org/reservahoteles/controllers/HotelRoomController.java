package org.reservahoteles.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reservahoteles.dto.*;
import org.reservahoteles.service.IHotelRoomService;
import org.reservahoteles.service.IHotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotelRooms")
@Log4j2
public class HotelRoomController {

    private final IHotelRoomService iHotelRoomService;

    @GetMapping("/getHotelRooms/all")
    public List<HotelRoomResponseDto> getListHotelRooms() {
        return iHotelRoomService.getHotelRooms();
    }

    @GetMapping("/getHotelRooms/hotel")
    public List<HotelRoomResponseDto> getListHotelRoomsByHotel(@RequestParam Long idHotel) {

        return iHotelRoomService.getHotelRoomsByHotel(idHotel);
    }

    @CrossOrigin("*")
    @PostMapping("/createHotelRoom")
    public ResponseEntity<ResponseDto> createHotelRoom(@Valid @RequestBody HotelRoomRequestDto hotelRoomRequestDto, BindingResult bindingResult) {

        ResponseDto response = new ResponseDto();

        // Si hay errores de validación, devolver mensajes de error
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            bindingResult.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            response.setMessage(errors.toString());
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setError(true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }


        response = iHotelRoomService.createHotelRoom(hotelRoomRequestDto);
        return ResponseEntity.ok(response);

    }
}
