package org.reservahoteles.service;

import org.reservahoteles.dto.HotelPhotoResponseDto;
import org.reservahoteles.dto.ImageHotelResponseDto;
import org.reservahoteles.dto.ResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IHotelPhotoService {

    List<HotelPhotoResponseDto> getHotelPhotosByHotel(Long idHotel);

    ResponseDto<ImageHotelResponseDto> createHotelPhoto(Long idHotel, MultipartFile file);
}
