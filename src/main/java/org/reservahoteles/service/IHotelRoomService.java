package org.reservahoteles.service;

import org.reservahoteles.dto.HotelRoomRequestDto;
import org.reservahoteles.dto.HotelRoomResponseDto;
import org.reservahoteles.dto.ResponseDto;
import org.reservahoteles.jpa.entities.HotelRoomEntity;

import java.util.List;

public interface IHotelRoomService {

    List<HotelRoomResponseDto> getHotelRooms();

    List<HotelRoomResponseDto> getHotelRoomsByHotel(Long IdHotel);

    ResponseDto createHotelRoom(HotelRoomRequestDto hotelRoomRequestDto);
}
