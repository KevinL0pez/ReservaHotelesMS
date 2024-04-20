package org.reservahoteles.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reservahoteles.dto.TypeRoomDto;
import org.reservahoteles.service.ITypeRoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/typeRooms")
@Log4j2
public class TypeRoomController {

    private final ITypeRoomService iTypeRoomService;

    @GetMapping("/getTypeRooms/all")
    public List<TypeRoomDto> getListTypeRooms() {
        return iTypeRoomService.getTypeRooms();
    }

}
