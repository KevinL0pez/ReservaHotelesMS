package org.reservahoteles.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.reservahoteles.jpa.entities.HotelEntity;

@Data
public class HotelPhotoRequestDto {

    @NotNull(message = "The photo url must not be null")
    private String photoUrl;

    private boolean active = true;

    @NotNull(message = "The hotel must not be null")
    private Long idHotel;
}
