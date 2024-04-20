package org.reservahoteles.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reservahoteles.dto.HotelResponseDto;
import org.reservahoteles.dto.HotelRequestDto;
import org.reservahoteles.dto.ResponseDto;
import org.reservahoteles.service.IHotelService;
import org.springframework.http.HttpStatus;
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
    public List<HotelResponseDto> getListHotels() {
        return iHotelService.getHotels();
    }

    @GetMapping("/gethotels/active")
    public List<HotelResponseDto> getListActiveHotels() {
        return iHotelService.getActiveHotels();
    }

    @GetMapping("/gethotels/department")
    public List<HotelResponseDto> getListHotelsByDepartment(@RequestParam Long idDepartment) {

        return iHotelService.getActiveHotelsByDepartment(idDepartment);
    }

    @GetMapping("/gethotels/municipality")
    public List<HotelResponseDto> getListHotelsByMunicipality(@RequestParam Long idMunicipality) {

        return iHotelService.getActiveHotelsByMunicipality(idMunicipality);
    }

    @CrossOrigin("*")
    @PostMapping("/createhotel")
    public ResponseEntity<ResponseDto> createHotel(@Valid @RequestBody HotelRequestDto hotelRequestDto, BindingResult bindingResult) {

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


        response = iHotelService.createHotel(hotelRequestDto);
        return ResponseEntity.ok(response);

    }

}
