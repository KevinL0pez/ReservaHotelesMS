package org.reservahoteles.service.implementation;

import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.utils.ObjectUtils;
import jakarta.transaction.Transactional;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class HotelPhotoService implements IHotelPhotoService {

    private final HotelRepository hotelRepository;
    private final Cloudinary cloudinary = Singleton.getCloudinary();
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
                        return hotelPhotoResponseDto;
                    })
                    .collect(Collectors.toList());
        }
    }

    @Override
    @Transactional
    public ResponseDto<ImageHotelResponseDto> createHotelPhoto(Long idHotel, MultipartFile file) {
        ResponseDto<ImageHotelResponseDto> responseDto = new ResponseDto<>();
        HotelPhotoEntity hotelPhoto = new HotelPhotoEntity();

        Optional<HotelEntity> hotelEntityOptional = hotelRepository.findById(idHotel);

        if (hotelEntityOptional.isEmpty()){
            responseDto.setMessage("Hotel not found");
            responseDto.setError(Boolean.TRUE);
            responseDto.setStatusCode(HttpStatus.NOT_FOUND);
            return responseDto;
        }

        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = uploadResult.get("secure_url").toString();
            HotelEntity hotel = hotelEntityOptional.get();
            hotelPhoto.setHotel(hotel);
            hotelPhoto.setPhotoName(file.getOriginalFilename());
            hotelPhoto.setPhotoUrl(url);
            hotelPhotoRepository.save(hotelPhoto);

            ImageHotelResponseDto imageHotelResponseDto = new ImageHotelResponseDto();
            imageHotelResponseDto.setUrlImage(url);
            responseDto.setMessage("Hotel Photo created successfully");
            responseDto.setError(Boolean.FALSE);
            responseDto.setStatusCode(HttpStatus.CREATED);
            responseDto.setData(imageHotelResponseDto);

            log.info("The hotel " + idHotel + " successfully uploaded the file: " + url);
        } catch (Exception ex) {
            responseDto.setError(Boolean.TRUE);
            responseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("The hotel " + idHotel + " failed to load to Cloudinary the image file: " + file.getName());
            log.error(ex.getMessage());
        }
        return responseDto;
    }
}
