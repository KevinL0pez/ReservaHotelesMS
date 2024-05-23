package org.reservahoteles.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reservahoteles.dto.*;
import org.reservahoteles.service.IHotelPhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotelPhotos")
@Log4j2
public class HotelPhotoController {

    private final IHotelPhotoService iHotelPhotoService;

    @CrossOrigin("*")
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
    public ResponseEntity<ResponseDto<ImageHotelResponseDto>> createHotelPhoto(
            @Validated @RequestParam("idHotel") Long idHotel,
            @Validated @RequestParam("file") MultipartFile file
    ) {
        ResponseDto<ImageHotelResponseDto> response = iHotelPhotoService.createHotelPhoto(idHotel, file);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
