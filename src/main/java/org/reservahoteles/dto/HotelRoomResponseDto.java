package org.reservahoteles.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.reservahoteles.jpa.entities.HotelEntity;
import org.reservahoteles.jpa.entities.TypeRoomEntity;

@Data
public class HotelRoomResponseDto {

    private Long idHotelRoom;

    private Integer roomCapacity;

    private Double pricePerNight;

    private boolean isAvailable = true;

    private String amenitiesDescription;

    private boolean active = true;

    //private HotelEntity hotel;

    private TypeRoomEntity typeRoom;
}
