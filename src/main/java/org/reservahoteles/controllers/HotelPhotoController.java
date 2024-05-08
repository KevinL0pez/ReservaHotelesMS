package org.reservahoteles.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reservahoteles.dto.*;
import org.reservahoteles.service.IHotelPhotoService;
import org.reservahoteles.service.IHotelRoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotelPhotos")
@Log4j2
public class HotelPhotoController {

    private final IHotelPhotoService iHotelPhotoService;

    @GetMapping("/getHotelPhotos/hotel")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ResponseDto<List<HotelPhotoResponseDto>>> getListHotelPhotosByHotel(@RequestParam Long idHotel) {

        List<HotelPhotoResponseDto> hotelPhotos = iHotelPhotoService.getHotelPhotosByHotel(idHotel);

        ResponseDto<List<HotelPhotoResponseDto>> responseDto = new ResponseDto<>();

        if (hotelPhotos == null || hotelPhotos.isEmpty()){
            responseDto.setMessage("Hotel Photos not found");
            responseDto.setStatusCode(HttpStatus.NOT_FOUND);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }else{
            responseDto.setMessage("Hotel Photos found successfully");
            responseDto.setStatusCode(HttpStatus.OK);
            responseDto.setData(hotelPhotos);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
    }

    @CrossOrigin("*")
    @PostMapping("/createHotelPhoto")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ResponseDto<HotelPhotoRequestDto>> createHotelPhoto(@Valid @RequestBody HotelPhotoRequestDto hotelPhotoRequestDto, BindingResult bindingResult) {

        ResponseDto<HotelPhotoRequestDto> response = new ResponseDto<>();

        // Si hay errores de validación, devolver mensajes de error
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            bindingResult.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            response.setMessage(errors.toString());
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            response.setError(true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response = iHotelPhotoService.createHotelPhoto(hotelPhotoRequestDto);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }
}
