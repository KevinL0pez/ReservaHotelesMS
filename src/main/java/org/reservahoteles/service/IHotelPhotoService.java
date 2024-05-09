package org.reservahoteles.service;

import org.reservahoteles.dto.HotelPhotoRequestDto;
import org.reservahoteles.dto.HotelPhotoResponseDto;
import org.reservahoteles.dto.ResponseDto;

import java.util.List;

public interface IHotelPhotoService {

    List<HotelPhotoResponseDto> getHotelPhotosByHotel(Long idHotel);

    ResponseDto<HotelPhotoRequestDto> createHotelPhoto(HotelPhotoRequestDto hotelPhotoRequestDto);
}
