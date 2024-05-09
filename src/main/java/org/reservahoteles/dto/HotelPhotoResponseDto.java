package org.reservahoteles.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.reservahoteles.jpa.entities.HotelEntity;

@Data
public class HotelPhotoResponseDto {

    private Long idHotelPhoto;

    private String photoUrl;

    private boolean active = true;

    //private HotelEntity hotel;
}
