package org.reservahoteles.service;

import jakarta.transaction.Transactional;
import org.reservahoteles.dto.StatusReservationDto;

import java.util.List;

public interface IStatusReservationService {

    @Transactional
    List<StatusReservationDto> getStatusReservations();
}
