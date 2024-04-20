package org.reservahoteles.service;

import org.reservahoteles.dto.*;

import java.util.List;

public interface IHotelService {

    List<HotelResponseDto> getHotels();

    List<HotelResponseDto> getActiveHotels();

    ResponseDto createHotel(HotelRequestDto hotelRequestDto);

    List<HotelResponseDto> getActiveHotelsByDepartment(Long idDepartment);

    List<HotelResponseDto> getActiveHotelsByMunicipality(Long idMunicipality);
}
