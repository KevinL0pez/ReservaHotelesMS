package org.reservahoteles.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reservahoteles.dto.MunicipalityDto;
import org.reservahoteles.dto.ResponseDto;
import org.reservahoteles.dto.TypeRoomDto;
import org.reservahoteles.service.ITypeRoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/typeRooms")
@Log4j2
public class TypeRoomController {

    private final ITypeRoomService iTypeRoomService;

    @GetMapping("/getTypeRooms/all")
    public ResponseEntity<ResponseDto<List<TypeRoomDto>>> getListTypeRooms() {

        ResponseDto<List<TypeRoomDto>> responseDto = new ResponseDto<>();
        List<TypeRoomDto> typeRooms = iTypeRoomService.getTypeRooms();
        if (typeRooms.isEmpty()){
            responseDto.setMessage("Type Rooms not found");
            responseDto.setStatusCode(HttpStatus.NOT_FOUND);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }else{
            responseDto.setMessage("Type Rooms found successfully");
            responseDto.setStatusCode(HttpStatus.OK);
            responseDto.setData(typeRooms);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
    }

}
