package org.reservahoteles.service;

import jakarta.transaction.Transactional;
import org.reservahoteles.dto.TypeRoomDto;

import java.util.List;

public interface ITypeRoomService {

    @Transactional
    List<TypeRoomDto> getTypeRooms();
}
