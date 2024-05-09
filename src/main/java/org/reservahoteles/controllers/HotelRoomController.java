package org.reservahoteles.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ResponseDto<List<HotelRoomResponseDto>>> getListHotelRooms() {
        List<HotelRoomResponseDto> hotelRooms = iHotelRoomService.getHotelRooms();
        ResponseDto<List<HotelRoomResponseDto>> responseDto = new ResponseDto<>();

        if (hotelRooms.isEmpty()){
            responseDto.setMessage("Hotel Rooms not found");
            responseDto.setStatusCode(HttpStatus.NOT_FOUND);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }else{
            responseDto.setMessage("Hotel Rooms found successfully");
            responseDto.setStatusCode(HttpStatus.OK);
            responseDto.setData(hotelRooms);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
    }

    @GetMapping("/getHotelRooms/hotel")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ResponseDto<List<HotelRoomResponseDto>>> getListHotelRoomsByHotel(@RequestParam Long idHotel) {

        List<HotelRoomResponseDto> hotelRooms = iHotelRoomService.getHotelRoomsByHotel(idHotel);

        ResponseDto<List<HotelRoomResponseDto>> responseDto = new ResponseDto<>();

        if (hotelRooms == null || hotelRooms.isEmpty()){
            responseDto.setMessage("Hotel Rooms not found");
            responseDto.setStatusCode(HttpStatus.NOT_FOUND);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }else{
            responseDto.setMessage("Hotel Rooms found successfully");
            responseDto.setStatusCode(HttpStatus.OK);
            responseDto.setData(hotelRooms);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
    }

    @CrossOrigin("*")
    @PostMapping("/createHotelRoom")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ResponseDto<HotelRoomRequestDto>> createHotelRoom(@Valid @RequestBody HotelRoomRequestDto hotelRoomRequestDto, BindingResult bindingResult) {

        ResponseDto<HotelRoomRequestDto> response = new ResponseDto<>();

        // Si hay errores de validación, devolver mensajes de error
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            bindingResult.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            response.setMessage(errors.toString());
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            response.setError(true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }


        response = iHotelRoomService.createHotelRoom(hotelRoomRequestDto);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }
}
