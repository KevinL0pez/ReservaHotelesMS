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
public class ReservationResponseDto {

    private Long idReservation;

    private UserEntity user;

    private HotelEntity hotel;

    private HotelRoomEntity hotelRoomEntity;

    private LocalDateTime checkInDatetime;

    private LocalDateTime checkOutDatetime;

    private Double totalPrice;

    private StatusReservationEntity statusReservation;
}
