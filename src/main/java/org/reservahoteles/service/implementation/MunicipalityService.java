package org.reservahoteles.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reservahoteles.dto.MunicipalityDto;
import org.reservahoteles.jpa.repositories.MunicipalityRepository;
import org.reservahoteles.service.IMunicipalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class MunicipalityService implements IMunicipalityService {

    @Autowired
    private final MunicipalityRepository municipalityRepository;

    @Override
    @Transactional
    public List<MunicipalityDto> getMunicipalities() {
        return municipalityRepository.findAll().stream()
                .map(municipality -> {
                    MunicipalityDto municipalityDto = new MunicipalityDto();
                    municipalityDto.setIdMunicipality(municipality.getIdMunicipality());
                    municipalityDto.setCodeMunicipality(municipality.getCodeMunicipality());
                    municipalityDto.setNameMunicipality(municipality.getNameMunicipality());
                    municipalityDto.setDepartment(municipality.getDepartment());
                    return municipalityDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<MunicipalityDto> getMunicipalitiesByDepartment(Long idDepartment) {
        return municipalityRepository.findByDepartmentIdDepartment(idDepartment).stream()
                .map(municipality -> {
                    MunicipalityDto municipalityDto = new MunicipalityDto();
                    municipalityDto.setIdMunicipality(municipality.getIdMunicipality());
                    municipalityDto.setCodeMunicipality(municipality.getCodeMunicipality());
                    municipalityDto.setNameMunicipality(municipality.getNameMunicipality());
                    municipalityDto.setDepartment(municipality.getDepartment());
                    return municipalityDto;
                })
                .collect(Collectors.toList());
    }
}
