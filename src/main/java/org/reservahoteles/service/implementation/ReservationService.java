package org.reservahoteles.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reservahoteles.dto.*;
import org.reservahoteles.jpa.entities.*;
import org.reservahoteles.jpa.repositories.*;
import org.reservahoteles.service.IReservationService;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReservationService implements IReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;
    private final HotelRoomRepository hotelRoomRepository;
    private final StatusReservationRepository statusReservationRepository;


    @Override
    public List<ReservationResponseDto> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(reservation -> {
                    ReservationResponseDto reservationResponseDto = new ReservationResponseDto();

                    HotelRoomResponseDto hotelRoomResponseDto = getHotelRoomResponseDto(reservation);
                    HotelResponseDtoV2 hotelResponseDto = getHotelResponseDto(reservation);

                    reservationResponseDto.setIdReservation(reservation.getIdReservation());
                    reservationResponseDto.setUser(reservation.getUser());
                    reservationResponseDto.setHotel(hotelResponseDto);
                    reservationResponseDto.setHotelRoomEntity(hotelRoomResponseDto);
                    reservationResponseDto.setCheckInDatetime(reservation.getCheckInDatetime());
                    reservationResponseDto.setCheckOutDatetime(reservation.getCheckOutDatetime());
                    reservationResponseDto.setTotalPrice(reservation.getTotalPrice());
                    reservationResponseDto.setStatusReservation(reservation.getStatusReservation());

                    return reservationResponseDto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<ReservationResponseDto> getFilteredReservationsByStatusId(Long idStatusReservation) {
        return reservationRepository.findByStatusReservationIdStatusReservation(idStatusReservation).stream()
                .map(reservation -> {

                    HotelRoomResponseDto hotelRoomResponseDto = getHotelRoomResponseDto(reservation);
                    HotelResponseDtoV2 hotelResponseDto = getHotelResponseDto(reservation);

                    ReservationResponseDto reservationResponseDto = new ReservationResponseDto();
                    reservationResponseDto.setIdReservation(reservation.getIdReservation());
                    reservationResponseDto.setUser(reservation.getUser());
                    reservationResponseDto.setHotel(hotelResponseDto);
                    reservationResponseDto.setHotelRoomEntity(hotelRoomResponseDto);
                    reservationResponseDto.setCheckInDatetime(reservation.getCheckInDatetime());
                    reservationResponseDto.setCheckOutDatetime(reservation.getCheckOutDatetime());
                    reservationResponseDto.setTotalPrice(reservation.getTotalPrice());
                    reservationResponseDto.setStatusReservation(reservation.getStatusReservation());

                    return reservationResponseDto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<ReservationResponseDto> getReservationsByUserId(Long idUser) {
        return reservationRepository.findByUserIdUser(idUser).stream()
                .map(reservation -> {
                    HotelRoomResponseDto hotelRoomResponseDto = getHotelRoomResponseDto(reservation);
                    HotelResponseDtoV2 hotelResponseDto = getHotelResponseDto(reservation);

                    ReservationResponseDto reservationResponseDto = new ReservationResponseDto();
                    reservationResponseDto.setIdReservation(reservation.getIdReservation());
                    reservationResponseDto.setUser(reservation.getUser());
                    reservationResponseDto.setHotel(hotelResponseDto);
                    reservationResponseDto.setHotelRoomEntity(hotelRoomResponseDto);
                    reservationResponseDto.setCheckInDatetime(reservation.getCheckInDatetime());
                    reservationResponseDto.setCheckOutDatetime(reservation.getCheckOutDatetime());
                    reservationResponseDto.setTotalPrice(reservation.getTotalPrice());
                    reservationResponseDto.setStatusReservation(reservation.getStatusReservation());

                    return reservationResponseDto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<ReservationResponseDto> getFilteredReservationsByUserIdAndStatusId(Long idUser, Long idStatusReservation) {
        return reservationRepository.findByUserIdUserAndStatusReservationIdStatusReservation(idUser, idStatusReservation).stream()
                .map(reservation -> {
                    HotelRoomResponseDto hotelRoomResponseDto = getHotelRoomResponseDto(reservation);
                    HotelResponseDtoV2 hotelResponseDto = getHotelResponseDto(reservation);

                    ReservationResponseDto reservationResponseDto = new ReservationResponseDto();
                    reservationResponseDto.setIdReservation(reservation.getIdReservation());
                    reservationResponseDto.setUser(reservation.getUser());
                    reservationResponseDto.setHotel(hotelResponseDto);
                    reservationResponseDto.setHotelRoomEntity(hotelRoomResponseDto);
                    reservationResponseDto.setCheckInDatetime(reservation.getCheckInDatetime());
                    reservationResponseDto.setCheckOutDatetime(reservation.getCheckOutDatetime());
                    reservationResponseDto.setTotalPrice(reservation.getTotalPrice());
                    reservationResponseDto.setStatusReservation(reservation.getStatusReservation());

                    return reservationResponseDto;
                }).collect(Collectors.toList());

    }


    @Override
    public List<ReservationResponseDto> getReservationsByCheckinAndCheckoutDates(LocalDateTime checkInDatetime, LocalDateTime checkOutDateTime){
        return reservationRepository.findReservationsByCheckinAndCheckoutDates(checkInDatetime, checkOutDateTime).stream()
                .map(reservation -> {
                    HotelRoomResponseDto hotelRoomResponseDto = getHotelRoomResponseDto(reservation);
                    HotelResponseDtoV2 hotelResponseDto = getHotelResponseDto(reservation);

                    ReservationResponseDto reservationResponseDto = new ReservationResponseDto();
                    reservationResponseDto.setIdReservation(reservation.getIdReservation());
                    reservationResponseDto.setUser(reservation.getUser());
                    reservationResponseDto.setHotel(hotelResponseDto);
                    reservationResponseDto.setHotelRoomEntity(hotelRoomResponseDto);
                    reservationResponseDto.setCheckInDatetime(reservation.getCheckInDatetime());
                    reservationResponseDto.setCheckOutDatetime(reservation.getCheckOutDatetime());
                    reservationResponseDto.setTotalPrice(reservation.getTotalPrice());
                    reservationResponseDto.setStatusReservation(reservation.getStatusReservation());

                    return reservationResponseDto;
                }).collect(Collectors.toList());
    }


    @Override
    public ResponseDto<ReservationRequestDto> createReservation(ReservationRequestDto reservationRequestDto) {

        ResponseDto<ReservationRequestDto> responseDto = new ResponseDto<>();

        //Filtrar por el hotel y la habitacion del hotel, si tiene alguna reservación activa, donde la fecha de checkin y de checkout
        //interfiera con la fecha de checkin y de checkout de la nueva reservación
        boolean reservationsValidations = false;
        Long idHotelRoom = reservationRequestDto.getIdHotelRoom();
        Long idHotel = reservationRequestDto.getIdHotel();

        List<Optional<ReservationEntity>> reservations = reservationRepository.findByHotelRoomEntityIdHotelRoomAndHotelIdHotelAndStatusReservationIdStatusReservation(idHotelRoom, idHotel, 1L);

        for (Optional<ReservationEntity> reservation : reservations) {
            if (reservation.isPresent()) {
                ReservationEntity reservationEntity = reservation.get();
                if (reservationRequestDto.getCheckInDatetime().isBefore(reservationEntity.getCheckOutDatetime()) && reservationRequestDto.getCheckOutDatetime().isAfter(reservationEntity.getCheckInDatetime())) {
                    reservationsValidations = true;
                    break;
                }
            }
        }

        //En caso de que la haya, retornar un mensaje de error, de lo contrario, crea la reservación
        if (reservationsValidations) {
            responseDto.setMessage("The room is already reserved for the selected dates");
            responseDto.setStatusCode(HttpStatus.CONFLICT);
            responseDto.setError(false);
            return responseDto;
        }
        else {
            ReservationEntity newReservation = new ReservationEntity();

            //Validations
            Optional<UserEntity> userValidation = userRepository.findById(reservationRequestDto.getIdUser());

            String validation_message = "";
            boolean validation_error = false;
            if (userValidation.isEmpty()){
                validation_message = "User not found";
                validation_error = true;
            }

            Optional<HotelEntity> hotelValidation = hotelRepository.findById(reservationRequestDto.getIdHotel());

            if (hotelValidation.isEmpty()){
                validation_message = "Hotel not found";
                validation_error = true;
            }

            Optional<HotelRoomEntity> hotelRoomValidation = hotelRoomRepository.findById(reservationRequestDto.getIdHotelRoom());

            if (hotelRoomValidation.isEmpty()){
                validation_message = "Hotel room not found";
                validation_error = true;
            }

            Optional<StatusReservationEntity> statusReservationValidation = statusReservationRepository.findById(reservationRequestDto.getIdStatusReservation());

            if (statusReservationValidation.isEmpty()){
                validation_message = "Status reservation not found";
                validation_error = true;
            }

            if (reservationRequestDto.getCheckInDatetime().isBefore(LocalDateTime.now())){
                validation_message = "Check in date must be greater than the current date";
                validation_error = true;
            }

            if (reservationRequestDto.getCheckOutDatetime().isBefore(reservationRequestDto.getCheckInDatetime())){
                validation_message = "Check out date must be greater than the check in date";
                validation_error = true;
            }

            if (validation_error){
                responseDto.setMessage(validation_message);
                responseDto.setStatusCode(HttpStatus.BAD_REQUEST);
                responseDto.setError(true);
                return responseDto;
            }else{
                UserEntity userReservation = userValidation.get();
                HotelEntity hotelReservation = hotelValidation.get();
                HotelRoomEntity hotelRoomReservation = hotelRoomValidation.get();
                StatusReservationEntity statusReservation = statusReservationValidation.get();

                newReservation.setUser(userReservation);
                newReservation.setHotel(hotelReservation);
                newReservation.setHotelRoomEntity(hotelRoomReservation);
                newReservation.setStatusReservation(statusReservation);
                newReservation.setCheckInDatetime(reservationRequestDto.getCheckInDatetime());
                newReservation.setCheckOutDatetime(reservationRequestDto.getCheckOutDatetime());

                //Calcular cuantas noches son entre el checkin y checkout, para asi poder calcular el precio final segun el price per night del hotel room

                long nightsReservation = reservationRequestDto.getCheckInDatetime().until(reservationRequestDto.getCheckOutDatetime(), java.time.temporal.ChronoUnit.DAYS);

                Double totalPrice = nightsReservation * hotelRoomReservation.getPricePerNight();

                newReservation.setTotalPrice(totalPrice);

                ReservationEntity reservationEntityCreated = reservationRepository.save(newReservation);

                responseDto.setMessage("Reservation created successfully");
                responseDto.setStatusCode(HttpStatus.CREATED);
                responseDto.setError(false);
                responseDto.setData(reservationRequestDto);
                return responseDto;
            }
        }
    }

    private static HotelResponseDtoV2 getHotelResponseDto(ReservationEntity reservation) {
        HotelResponseDtoV2 hotelResponseDto = new HotelResponseDtoV2();

        DepartmentDto departmentDto = new DepartmentDto();

        departmentDto.setIdDepartment(reservation.getHotel().getDepartment().getIdDepartment());
        departmentDto.setNameDepartment(reservation.getHotel().getDepartment().getNameDepartment());
        departmentDto.setCodeDepartment(reservation.getHotel().getDepartment().getCodeDepartment());

        MunicipalityDto municipalityDto = new MunicipalityDto();

        municipalityDto.setIdMunicipality(reservation.getHotel().getMunicipality().getIdMunicipality());
        municipalityDto.setCodeMunicipality(reservation.getHotel().getMunicipality().getCodeMunicipality());
        municipalityDto.setNameMunicipality(reservation.getHotel().getMunicipality().getNameMunicipality());


        hotelResponseDto.setIdHotel(reservation.getHotel().getIdHotel());
        hotelResponseDto.setNameHotel(reservation.getHotel().getNameHotel());
        hotelResponseDto.setAddressHotel(reservation.getHotel().getAddressHotel());
        hotelResponseDto.setDescriptionHotel(reservation.getHotel().getDescriptionHotel());
        hotelResponseDto.setHotelPhone(reservation.getHotel().getHotelPhone());
        hotelResponseDto.setClassificationHotel(reservation.getHotel().getClassificationHotel());
        hotelResponseDto.setHotelActive(reservation.getHotel().isHotelActive());
        hotelResponseDto.setDepartment(departmentDto);
        hotelResponseDto.setMunicipality(municipalityDto);
        return hotelResponseDto;
    }

    private static HotelRoomResponseDto getHotelRoomResponseDto(ReservationEntity reservation) {
        HotelRoomResponseDto hotelRoomResponseDto = new HotelRoomResponseDto();

        hotelRoomResponseDto.setIdHotelRoom(reservation.getHotelRoomEntity().getIdHotelRoom());
        hotelRoomResponseDto.setRoomCapacity(reservation.getHotelRoomEntity().getRoomCapacity());
        hotelRoomResponseDto.setPricePerNight(reservation.getHotelRoomEntity().getPricePerNight());
        hotelRoomResponseDto.setAmenitiesDescription(reservation.getHotelRoomEntity().getAmenitiesDescription());
        hotelRoomResponseDto.setActive(reservation.getHotelRoomEntity().isActive());
        hotelRoomResponseDto.setTypeRoom(reservation.getHotelRoomEntity().getTypeRoom());
        return hotelRoomResponseDto;
    }
}
