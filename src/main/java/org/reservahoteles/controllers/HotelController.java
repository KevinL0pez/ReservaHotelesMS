package org.reservahoteles.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reservahoteles.dto.HotelResponseDto;
import org.reservahoteles.dto.HotelRequestDto;
import org.reservahoteles.dto.ResponseDto;
import org.reservahoteles.service.IHotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
@Log4j2
public class HotelController {

    private final IHotelService iHotelService;

    @GetMapping("/gethotels/all")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ResponseDto<List<HotelResponseDto>>> getListHotels() {

        ResponseDto<List<HotelResponseDto>> responseDto = new ResponseDto<>();

        List<HotelResponseDto> hoteles = iHotelService.getHotels();
        if (hoteles.isEmpty()){
            responseDto.setMessage("Hotels not found");
            responseDto.setStatusCode(HttpStatus.NOT_FOUND);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }else{
            responseDto.setMessage("Hotels found successfully");
            responseDto.setStatusCode(HttpStatus.OK);
            responseDto.setData(hoteles);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }

    }

    @GetMapping("/gethotels/active")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ResponseDto<List<HotelResponseDto>>> getListActiveHotels() {
        ResponseDto<List<HotelResponseDto>> responseDto = new ResponseDto<>();

        List<HotelResponseDto> hoteles = iHotelService.getActiveHotels();
        if (hoteles.isEmpty()){
            responseDto.setMessage("Hotels not found");
            responseDto.setStatusCode(HttpStatus.NOT_FOUND);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }else{
            responseDto.setMessage("Hotels found successfully");
            responseDto.setStatusCode(HttpStatus.OK);
            responseDto.setData(hoteles);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
    }

    @GetMapping("/gethotels/department")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ResponseDto<List<HotelResponseDto>>> getListHotelsByDepartment(@RequestParam Long idDepartment) {

        ResponseDto<List<HotelResponseDto>> responseDto = new ResponseDto<>();

        List<HotelResponseDto> hoteles = iHotelService.getActiveHotelsByDepartment(idDepartment);
        if (hoteles == null || hoteles.isEmpty()){
            responseDto.setMessage("Hotels not found");
            responseDto.setStatusCode(HttpStatus.NOT_FOUND);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }else{
            responseDto.setMessage("Hotels found successfully");
            responseDto.setStatusCode(HttpStatus.OK);
            responseDto.setData(hoteles);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
    }

    @GetMapping("/gethotels/municipality")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ResponseDto<List<HotelResponseDto>>> getListHotelsByMunicipality(@RequestParam Long idMunicipality) {

        ResponseDto<List<HotelResponseDto>> responseDto = new ResponseDto<>();

        List<HotelResponseDto> hoteles = iHotelService.getActiveHotelsByMunicipality(idMunicipality);
        if (hoteles == null || hoteles.isEmpty()){
            responseDto.setMessage("Hotels not found");
            responseDto.setStatusCode(HttpStatus.NOT_FOUND);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }else{
            responseDto.setMessage("Hotels found successfully");
            responseDto.setStatusCode(HttpStatus.OK);
            responseDto.setData(hoteles);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
    }

    @CrossOrigin("*")
    @PostMapping("/createhotel")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ResponseDto<HotelRequestDto>> createHotel(@Valid @RequestBody HotelRequestDto hotelRequestDto, BindingResult bindingResult) {

        ResponseDto<HotelRequestDto> response = new ResponseDto<>();

        // Si hay errores de validación, devolver mensajes de error
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            bindingResult.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            response.setMessage(errors.toString());
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            response.setError(true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }


        response = iHotelService.createHotel(hotelRequestDto);
        return ResponseEntity.ok(response);

    }

}
