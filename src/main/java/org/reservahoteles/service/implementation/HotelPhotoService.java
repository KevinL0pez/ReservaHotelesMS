package org.reservahoteles.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reservahoteles.dto.*;
import org.reservahoteles.jpa.entities.HotelEntity;
import org.reservahoteles.jpa.entities.HotelPhotoEntity;
import org.reservahoteles.jpa.repositories.HotelPhotoRepository;
import org.reservahoteles.jpa.repositories.HotelRepository;
import org.reservahoteles.service.IHotelPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class HotelPhotoService implements IHotelPhotoService{

    @Autowired
    private final HotelRepository hotelRepository;

    @Autowired
    private final HotelPhotoRepository hotelPhotoRepository;


    @Override
    public List<HotelPhotoResponseDto> getHotelPhotosByHotel(Long idHotel) {
        HotelEntity hotel = hotelRepository.findById(idHotel).orElse(null);
        if (hotel == null){
            return null;
        }
        else{
            return hotelPhotoRepository.findByHotel(hotel).stream()
                    .map(hotelPhotoEntity -> {
                        HotelPhotoResponseDto hotelPhotoResponseDto = new HotelPhotoResponseDto();

                        hotelPhotoResponseDto.setIdHotelPhoto(hotelPhotoEntity.getIdHotelPhoto());
                        hotelPhotoResponseDto.setPhotoUrl(hotelPhotoEntity.getPhotoUrl());
                        hotelPhotoResponseDto.setActive(hotelPhotoEntity.isActive());
                        return hotelPhotoResponseDto;
                    })
                    .collect(Collectors.toList());
        }
    }

    @Override
    public ResponseDto<HotelPhotoRequestDto> createHotelPhoto(HotelPhotoRequestDto hotelPhotoRequestDto) {
        ResponseDto<HotelPhotoRequestDto> responseDto = new ResponseDto<>();
        HotelPhotoEntity hotelPhoto = new HotelPhotoEntity();

        Optional<HotelEntity> hotelEntityOptional = hotelRepository.findById(hotelPhotoRequestDto.getIdHotel());

        if (hotelEntityOptional.isEmpty()){
            responseDto.setMessage("Hotel not found");
            responseDto.setError(Boolean.TRUE);
            responseDto.setStatusCode(HttpStatus.NOT_FOUND);
            return responseDto;
        }
        HotelEntity hotel = hotelEntityOptional.get();

        hotelPhoto.setHotel(hotel);
        hotelPhoto.setPhotoUrl(hotelPhotoRequestDto.getPhotoUrl());

        hotelPhotoRepository.save(hotelPhoto);

        responseDto.setMessage("Hotel Photo created successfully");
        responseDto.setError(Boolean.FALSE);
        responseDto.setStatusCode(HttpStatus.CREATED);
        responseDto.setData(hotelPhotoRequestDto);

        return responseDto;

    }
}
