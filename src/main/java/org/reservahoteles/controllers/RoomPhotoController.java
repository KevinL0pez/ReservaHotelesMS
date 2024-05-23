package org.reservahoteles.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reservahoteles.dto.*;
import org.reservahoteles.service.IRoomPhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roomPhotos")
@Slf4j
public class RoomPhotoController {

    private final IRoomPhotoService iRoomPhotoService;

    @CrossOrigin("*")
    @GetMapping("/getRoomPhotos/hotel")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ResponseDto<List<RoomPhotoResponseDto>>> getListHotelPhotosByHotel(@RequestParam Long idRoom) {

        List<RoomPhotoResponseDto> roomPhotos = iRoomPhotoService.getPhotosByRoom(idRoom);

        ResponseDto<List<RoomPhotoResponseDto>> responseDto = new ResponseDto<>();

        if (roomPhotos == null || roomPhotos.isEmpty()) {
            responseDto.setMessage("Room Photos not found");
            responseDto.setStatusCode(HttpStatus.NOT_FOUND);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        } else {
            responseDto.setMessage("Room Photos found successfully");
            responseDto.setStatusCode(HttpStatus.OK);
            responseDto.setData(roomPhotos);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
    }

    @CrossOrigin("*")
    @PostMapping("/createRoomPhoto")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<ResponseDto<ImageRoomResponseDto>> createHotelPhoto(
            @Validated @RequestParam("idRoom") Long idRoom,
            @Validated @RequestParam("file") MultipartFile file
    ) {
        ResponseDto<ImageRoomResponseDto> response = iRoomPhotoService.createRoomPhoto(idRoom, file);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
