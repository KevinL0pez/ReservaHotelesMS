package org.reservahoteles.service;

import org.reservahoteles.dto.ReservationRequestDto;
import org.reservahoteles.dto.ReservationResponseDto;
import org.reservahoteles.dto.ResponseDto;
import org.reservahoteles.jpa.entities.ReservationEntity;

import java.util.List;

public interface IReservationService {

    List<ReservationResponseDto> getAllReservations();

    List<ReservationResponseDto> getFilteredReservationsByStatusId(Long idStatusReservation);

    List<ReservationResponseDto> getReservationsByUserId(Long idUser);

    List<ReservationResponseDto> getFilteredReservationsByUserIdAndStatusId(Long idUser, Long idStatusReservation);

    ResponseDto<ReservationRequestDto> createReservation(ReservationRequestDto reservationRequestDto);

}
