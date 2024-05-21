package org.reservahoteles.service.implementation;

import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.utils.ObjectUtils;
import jakarta.transaction.Transactional;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reservahoteles.dto.*;
import org.reservahoteles.jpa.entities.HotelRoomEntity;
import org.reservahoteles.jpa.entities.RoomPhotoEntity;
import org.reservahoteles.jpa.repositories.HotelRoomRepository;
import org.reservahoteles.jpa.repositories.RoomPhotoRepository;
import org.reservahoteles.service.IRoomPhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class RoomPhotoService implements IRoomPhotoService {

    private final HotelRoomRepository hotelRoomRepository;
    private final Cloudinary cloudinary = Singleton.getCloudinary();
    private final RoomPhotoRepository roomPhotoRepository;

    @Override
    public List<RoomPhotoResponseDto> getPhotosByRoom(Long idRoom) {
        HotelRoomEntity room = hotelRoomRepository.findById(idRoom).orElse(null);
        if (room == null) return Collections.emptyList();
        return roomPhotoRepository.findByRoom(room).stream()
                .map(roomPhotoEntity -> {
                    RoomPhotoResponseDto roomPhotoResponseDto = new RoomPhotoResponseDto();
                    roomPhotoResponseDto.setIdRoom(roomPhotoEntity.getRoom().getIdHotelRoom());
                    roomPhotoResponseDto.setUrlImage(roomPhotoEntity.getPhotoUrl());
                    return roomPhotoResponseDto;
                }).toList();

    }

    @Override
    @Transactional
    public ResponseDto<ImageRoomResponseDto> createRoomPhoto(Long idRoom, MultipartFile file) {
        ResponseDto<ImageRoomResponseDto> responseDto = new ResponseDto<>();
        RoomPhotoEntity roomPhoto = new RoomPhotoEntity();

        Optional<HotelRoomEntity> hotelRoomEntity = hotelRoomRepository.findById(idRoom);

        if (hotelRoomEntity.isEmpty()){
            responseDto.setMessage("Room not found");
            responseDto.setError(Boolean.TRUE);
            responseDto.setStatusCode(HttpStatus.NOT_FOUND);
            return responseDto;
        }

        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = uploadResult.get("secure_url").toString();
            HotelRoomEntity room = hotelRoomEntity.get();
            roomPhoto.setRoom(room);
            roomPhoto.setPhotoName(file.getOriginalFilename());
            roomPhoto.setPhotoUrl(url);
            roomPhotoRepository.save(roomPhoto);

            ImageRoomResponseDto imageRoomResponseDto = new ImageRoomResponseDto();
            imageRoomResponseDto.setUrlImage(url);
            responseDto.setMessage("Hotel Photo created successfully");
            responseDto.setError(Boolean.FALSE);
            responseDto.setStatusCode(HttpStatus.CREATED);
            responseDto.setData(imageRoomResponseDto);

            log.info("The hotel " + idRoom + " successfully uploaded the file: " + url);
        } catch (Exception ex) {
            responseDto.setError(Boolean.TRUE);
            responseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("The hotel " + idRoom + " failed to load to Cloudinary the image file: " + file.getName());
            log.error(ex.getMessage());
        }
        return responseDto;
    }

}
