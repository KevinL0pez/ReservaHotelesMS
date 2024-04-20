package org.reservahoteles.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reservahoteles.dto.DepartmentDto;
import org.reservahoteles.dto.TypeRoomDto;
import org.reservahoteles.jpa.repositories.TypeRoomRepository;
import org.reservahoteles.service.ITypeRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class TypeRoomService implements ITypeRoomService {

    @Autowired
    private final TypeRoomRepository typeRoomRepository;
    @Override
    public List<TypeRoomDto> getTypeRooms() {
        return typeRoomRepository.findAll().stream()
                .map(typeRoom-> {
                    TypeRoomDto typeRoomDto = new TypeRoomDto();
                    typeRoomDto.setIdTypeRoom(typeRoom.getIdTypeRoom());
                    typeRoomDto.setTitleTypeRoom(typeRoom.getTitleTypeRoom());
                    return typeRoomDto;
                }).collect(Collectors.toList());
    }
}
