package org.reservahoteles.dto;

import lombok.Data;
import org.reservahoteles.jpa.entities.HotelEntity;
import org.reservahoteles.jpa.entities.TypeRoomEntity;

@Data
public class HotelRoomRequestDto {

    private Integer roomCapacity;

    private Double pricePerNight;

    private boolean isAvailable = true;

    private String amenitiesDescription;

    private boolean active = true;

    private Long idHotel;

    private Long idTypeRoom;
}
