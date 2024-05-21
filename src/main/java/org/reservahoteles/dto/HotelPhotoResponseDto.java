package org.reservahoteles.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.reservahoteles.jpa.entities.HotelEntity;

import java.util.UUID;

@Data
public class HotelPhotoResponseDto {

    private UUID idHotelPhoto;
    private String photoUrl;
//    private HotelEntity hotel;
}
