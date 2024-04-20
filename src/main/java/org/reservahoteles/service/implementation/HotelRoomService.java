package org.reservahoteles.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reservahoteles.dto.HotelRoomRequestDto;
import org.reservahoteles.dto.HotelRoomResponseDto;
import org.reservahoteles.dto.ResponseDto;
import org.reservahoteles.jpa.entities.*;
import org.reservahoteles.jpa.repositories.HotelRepository;
import org.reservahoteles.jpa.repositories.HotelRoomRepository;
import org.reservahoteles.jpa.repositories.TypeRoomRepository;
import org.reservahoteles.service.IHotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class HotelRoomService implements IHotelRoomService {

    @Autowired
    private final HotelRoomRepository hotelRoomRepository;

    @Autowired
    private final HotelRepository hotelRepository;

    @Autowired
    private final TypeRoomRepository typeRoomRepository;

    @Override
    public List<HotelRoomResponseDto> getHotelRooms() {
        return hotelRoomRepository.findAll().stream()
                .map(hotelRoomEntity -> {
                    HotelRoomResponseDto hotelRoomResponseDto = new HotelRoomResponseDto();

                    hotelRoomResponseDto.setIdHotelRoom(hotelRoomEntity.getIdHotelRoom());
                    hotelRoomResponseDto.setRoomCapacity(hotelRoomEntity.getRoomCapacity());
                    hotelRoomResponseDto.setPricePerNight(hotelRoomEntity.getPricePerNight());
                    hotelRoomResponseDto.setAvailable(hotelRoomEntity.isAvailable());
                    hotelRoomResponseDto.setAmenitiesDescription(hotelRoomEntity.getAmenitiesDescription());
                    hotelRoomResponseDto.setActive(hotelRoomEntity.isActive());
                    //hotelRoomResponseDto.setHotel(hotelRoomEntity.getHotel());
                    hotelRoomResponseDto.setTypeRoom(hotelRoomEntity.getTypeRoom());
                    return hotelRoomResponseDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<HotelRoomResponseDto> getHotelRoomsByHotel(Long IdHotel) {

        HotelEntity hotel = hotelRepository.findById(IdHotel).orElse(null);
        if (hotel == null){
            return null;
        }
        else{
            return hotelRoomRepository.findByHotel(hotel).stream()
                    .map(hotelRoomEntity -> {
                        HotelRoomResponseDto hotelRoomResponseDto = new HotelRoomResponseDto();

                        hotelRoomResponseDto.setIdHotelRoom(hotelRoomEntity.getIdHotelRoom());
                        hotelRoomResponseDto.setRoomCapacity(hotelRoomEntity.getRoomCapacity());
                        hotelRoomResponseDto.setPricePerNight(hotelRoomEntity.getPricePerNight());
                        hotelRoomResponseDto.setAvailable(hotelRoomEntity.isAvailable());
                        hotelRoomResponseDto.setAmenitiesDescription(hotelRoomEntity.getAmenitiesDescription());
                        hotelRoomResponseDto.setActive(hotelRoomEntity.isActive());
                        //hotelRoomResponseDto.setHotel(hotelRoomEntity.getHotel());
                        hotelRoomResponseDto.setTypeRoom(hotelRoomEntity.getTypeRoom());
                        return hotelRoomResponseDto;
                    })
                    .collect(Collectors.toList());
        }

    }

    @Override
    public ResponseDto createHotelRoom(HotelRoomRequestDto hotelRoomRequestDto) {
        ResponseDto responseDto = new ResponseDto();
        HotelRoomEntity hotelRoom = new HotelRoomEntity();

        Long idHotel = hotelRoomRequestDto.getIdHotel();

        Optional<HotelEntity> hotelEntityOptional = hotelRepository.findById(idHotel);

        if (hotelEntityOptional.isEmpty()){
            responseDto.setMessage("Hotel not found");
            responseDto.setError(Boolean.TRUE);
            responseDto.setStatusCode(HttpStatus.NOT_FOUND.value());
            return responseDto;
        }
        HotelEntity hotel = hotelEntityOptional.get();

        Long idTypeRoom = hotelRoomRequestDto.getIdTypeRoom();
        Optional<TypeRoomEntity> typeRoomEntityOptional = typeRoomRepository.findById(idTypeRoom);
        if (typeRoomEntityOptional.isEmpty()) {
            responseDto.setMessage("Type room not found");
            responseDto.setError(Boolean.TRUE);
            responseDto.setStatusCode(HttpStatus.NOT_FOUND.value());
            return responseDto;
        }

        TypeRoomEntity typeRoom = typeRoomEntityOptional.get();

        hotelRoom.setRoomCapacity(hotelRoomRequestDto.getRoomCapacity());
        hotelRoom.setPricePerNight(hotelRoomRequestDto.getPricePerNight());
        hotelRoom.setAvailable(hotelRoomRequestDto.isAvailable());
        hotelRoom.setAmenitiesDescription(hotelRoomRequestDto.getAmenitiesDescription());
        hotelRoom.setActive(hotelRoomRequestDto.isActive());
        hotelRoom.setHotel(hotel);
        hotelRoom.setTypeRoom(typeRoom);


        hotelRoomRepository.save(hotelRoom);

        responseDto.setMessage("Hotel Room created successfully");
        responseDto.setError(Boolean.FALSE);
        responseDto.setStatusCode(HttpStatus.CREATED.value());

        return responseDto;
    }
}
