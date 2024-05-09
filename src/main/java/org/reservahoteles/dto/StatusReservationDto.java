package org.reservahoteles.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class StatusReservationDto {

    private Long idStatusReservation;

    private String titleStatusReservation;
}
