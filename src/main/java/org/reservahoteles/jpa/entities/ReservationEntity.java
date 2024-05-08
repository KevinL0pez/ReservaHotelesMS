package org.reservahoteles.jpa.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Reservations")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Long idReservation;

    @NotNull(message = "The user must not be null")
    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @NotNull(message = "The hotel must not be null")
    @ManyToOne
    @JoinColumn(name = "id_hotel")
    private HotelEntity hotel;

    @NotNull(message = "The hotel room must not be null")
    @ManyToOne
    @JoinColumn(name = "id_hotel_room")
    private HotelRoomEntity hotelRoomEntity;

    //Date and time of check in and check out
    @Column(name= "check_in_datetime")
    private LocalDateTime checkInDatetime;

    @Column(name= "check_out_datetime")
    private LocalDateTime checkOutDatetime;

    @Column(name= "total_price")
    private Double totalPrice;

    @NotNull(message = "The status reservation must not be null")
    @ManyToOne
    @JoinColumn(name = "id_status_reservation")
    private StatusReservationEntity statusReservation;


}
