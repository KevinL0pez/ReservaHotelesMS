package org.reservahoteles.jpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "HotelRooms")
public class HotelRoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hotel_room")
    private Long idHotelRoom;

    @Column(name = "room_capacity")
    private Integer roomCapacity;

    @Column(name = "price_per_night")
    private Double pricePerNight;

    @Column(name= "is_available")
    private boolean isAvailable = true;

    @Column(name = "amenities_description")
    private String amenitiesDescription;

    @Column(name= "active")
    private boolean active = true;

    @NotNull(message = "The hotel for the room must not be null")
    @ManyToOne
    @JoinColumn(name = "id_hotel")
    private HotelEntity hotel;

    @NotNull(message = "The type room must not be null")
    @ManyToOne
    @JoinColumn(name = "id_type_room")
    private TypeRoomEntity typeRoom;

}
