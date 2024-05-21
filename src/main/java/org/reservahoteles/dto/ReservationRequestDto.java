package org.reservahoteles.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.reservahoteles.jpa.entities.HotelEntity;
import org.reservahoteles.jpa.entities.HotelRoomEntity;
import org.reservahoteles.jpa.entities.StatusReservationEntity;
import org.reservahoteles.jpa.entities.UserEntity;

import java.time.LocalDateTime;

@Data
public class ReservationRequestDto {

    private Long idUser;

    private Long idHotel;

    private Long idHotelRoom;

    private LocalDateTime checkInDatetime;

    private LocalDateTime checkOutDatetime;

    private Long idStatusReservation;
}
