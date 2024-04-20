package org.reservahoteles.service.implementation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reservahoteles.dto.HotelResponseDto;
import org.reservahoteles.dto.HotelRequestDto;
import org.reservahoteles.dto.ResponseDto;
import org.reservahoteles.jpa.entities.DepartmentEntity;
import org.reservahoteles.jpa.entities.HotelEntity;
import org.reservahoteles.jpa.entities.MunicipalityEntity;
import org.reservahoteles.jpa.repositories.DepartmentRepository;
import org.reservahoteles.jpa.repositories.HotelRepository;
import org.reservahoteles.jpa.repositories.MunicipalityRepository;
import org.reservahoteles.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class HotelService implements IHotelService {

    @Autowired
    private final HotelRepository hotelRepository;
    @Autowired
    private final DepartmentRepository departmentRepository;
    @Autowired
    private final MunicipalityRepository municipalityRepository;

    @Override
    public List<HotelResponseDto> getHotels() {
        return hotelRepository.findAll().stream()
                .map(hotel -> {
                    HotelResponseDto hotelResponseDto = new HotelResponseDto();
                    hotelResponseDto.setIdHotel(hotel.getIdHotel());
                    hotelResponseDto.setNameHotel(hotel.getNameHotel());
                    hotelResponseDto.setAddressHotel(hotel.getAddressHotel());
                    hotelResponseDto.setDescriptionHotel(hotel.getDescriptionHotel());
                    hotelResponseDto.setHotelPhone(hotel.getHotelPhone());
                    hotelResponseDto.setClassificationHotel(hotel.getClassificationHotel());
                    hotelResponseDto.setHotelActive(hotel.isHotelActive());
                    hotelResponseDto.setDepartment(hotel.getDepartment());
                    hotelResponseDto.setMunicipality(hotel.getMunicipality());
                    return hotelResponseDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<HotelResponseDto> getActiveHotels() {
        return hotelRepository.findAll().stream()
                .filter(hotel -> hotel.isHotelActive())
                .map(hotel -> {
                    HotelResponseDto hotelResponseDto = new HotelResponseDto();
                    hotelResponseDto.setIdHotel(hotel.getIdHotel());
                    hotelResponseDto.setNameHotel(hotel.getNameHotel());
                    hotelResponseDto.setAddressHotel(hotel.getAddressHotel());
                    hotelResponseDto.setDescriptionHotel(hotel.getDescriptionHotel());
                    hotelResponseDto.setHotelPhone(hotel.getHotelPhone());
                    hotelResponseDto.setClassificationHotel(hotel.getClassificationHotel());
                    hotelResponseDto.setHotelActive(hotel.isHotelActive());
                    hotelResponseDto.setDepartment(hotel.getDepartment());
                    hotelResponseDto.setMunicipality(hotel.getMunicipality());
                    return hotelResponseDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ResponseDto createHotel(@Valid HotelRequestDto hotelRequestDto) {
        ResponseDto responseDto = new ResponseDto();
        HotelEntity hotel = new HotelEntity();

        Long idMunicipalityHotel = hotelRequestDto.getIdMunicipality();

        Optional<MunicipalityEntity> municipalityHotelOptional = municipalityRepository.findById(idMunicipalityHotel);
        //System.out.println("municipality_hotel" + municipality_hotel);
        if (municipalityHotelOptional.isEmpty()){
            responseDto.setMessage("Municipality not found");
            responseDto.setError(Boolean.TRUE);
            responseDto.setStatus_code(HttpStatus.NOT_FOUND.value());
            return responseDto;
        }
        MunicipalityEntity municipality = municipalityHotelOptional.get();
        HotelEntity hotel_validation = hotelRepository.findByNameHotelAndAddressHotelAndMunicipality(
                hotelRequestDto.getNameHotel(),
                hotelRequestDto.getAddressHotel(),
                municipality
        );

        if (hotel_validation != null) {
            responseDto.setMessage("Hotel already exists");
            responseDto.setError(Boolean.TRUE);
            responseDto.setStatus_code(HttpStatus.CONFLICT.value());
            return responseDto;
        }

        hotel.setNameHotel(hotelRequestDto.getNameHotel());
        hotel.setAddressHotel(hotelRequestDto.getAddressHotel());
        hotel.setDescriptionHotel(hotelRequestDto.getDescriptionHotel());
        hotel.setHotelPhone(hotelRequestDto.getHotelPhone());
        hotel.setClassificationHotel(hotelRequestDto.getClassificationHotel());
        hotel.setHotelActive(hotelRequestDto.isHotelActive());

        Long idDepartmentHotel = hotelRequestDto.getIdDepartment();
        Optional<DepartmentEntity> departmentHotelOptional = departmentRepository.findById(idDepartmentHotel);
        if (departmentHotelOptional.isEmpty()) {
            responseDto.setMessage("Department not found");
            responseDto.setError(Boolean.TRUE);
            responseDto.setStatus_code(HttpStatus.NOT_FOUND.value());
            return responseDto;
        }

        //System.out.println(municipality_hotel);
        hotel.setDepartment(departmentHotelOptional.get());
        hotel.setMunicipality(municipalityHotelOptional.get());

        hotelRepository.save(hotel);

        responseDto.setMessage("Hotel created successfully");
        responseDto.setError(Boolean.FALSE);
        responseDto.setStatus_code(HttpStatus.CREATED.value());


        return responseDto;
    }

    @Override
    public List<HotelResponseDto> getActiveHotelsByDepartment(Long idDepartment) {

        DepartmentEntity department =departmentRepository.findById(idDepartment).orElse(null);
        if (department == null) {
            return null;
        }
        else{
            return hotelRepository.findByDepartment(department).stream()
                    .filter(hotel -> hotel.isHotelActive())
                    .map(hotel -> {
                        HotelResponseDto hotelResponseDto = new HotelResponseDto();
                        hotelResponseDto.setIdHotel(hotel.getIdHotel());
                        hotelResponseDto.setNameHotel(hotel.getNameHotel());
                        hotelResponseDto.setAddressHotel(hotel.getAddressHotel());
                        hotelResponseDto.setDescriptionHotel(hotel.getDescriptionHotel());
                        hotelResponseDto.setHotelPhone(hotel.getHotelPhone());
                        hotelResponseDto.setClassificationHotel(hotel.getClassificationHotel());
                        hotelResponseDto.setHotelActive(hotel.isHotelActive());
                        hotelResponseDto.setDepartment(hotel.getDepartment());
                        hotelResponseDto.setMunicipality(hotel.getMunicipality());
                        return hotelResponseDto;
                    })
                    .collect(Collectors.toList());
        }

    }

    @Override
    public List<HotelResponseDto> getActiveHotelsByMunicipality(Long idMunicipality) {

        MunicipalityEntity municipality = municipalityRepository.findById(idMunicipality).orElse(null);
        if (municipality == null) {
            return null;
        }
        else{
            return hotelRepository.findByMunicipality(municipality).stream()
                    .filter(hotel -> hotel.isHotelActive())
                    .map(hotel -> {
                        HotelResponseDto hotelResponseDto = new HotelResponseDto();
                        hotelResponseDto.setIdHotel(hotel.getIdHotel());
                        hotelResponseDto.setNameHotel(hotel.getNameHotel());
                        hotelResponseDto.setAddressHotel(hotel.getAddressHotel());
                        hotelResponseDto.setDescriptionHotel(hotel.getDescriptionHotel());
                        hotelResponseDto.setHotelPhone(hotel.getHotelPhone());
                        hotelResponseDto.setClassificationHotel(hotel.getClassificationHotel());
                        hotelResponseDto.setHotelActive(hotel.isHotelActive());
                        hotelResponseDto.setDepartment(hotel.getDepartment());
                        hotelResponseDto.setMunicipality(hotel.getMunicipality());
                        return hotelResponseDto;
                    })
                    .collect(Collectors.toList());
        }
    }
}
