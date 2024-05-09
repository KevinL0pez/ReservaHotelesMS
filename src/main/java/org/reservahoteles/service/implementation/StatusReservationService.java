package org.reservahoteles.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reservahoteles.dto.StatusReservationDto;
import org.reservahoteles.jpa.repositories.StatusReservationRepository;
import org.reservahoteles.service.IStatusReservationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class StatusReservationService implements IStatusReservationService {

    private final StatusReservationRepository statusReservationRepository;
    @Override
    public List<StatusReservationDto> getStatusReservations() {
        return null;
    }
}
