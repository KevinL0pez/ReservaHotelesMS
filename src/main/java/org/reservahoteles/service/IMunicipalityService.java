package org.reservahoteles.service;

import jakarta.transaction.Transactional;

import org.reservahoteles.dto.MunicipalityDto;
import org.reservahoteles.jpa.entities.MunicipalityEntity;

import java.util.List;

public interface IMunicipalityService {
    @Transactional
    List<MunicipalityDto> getMunicipalities();

    @Transactional
    List<MunicipalityDto> getMunicipalitiesByDepartment(Long idDepartment);

}
