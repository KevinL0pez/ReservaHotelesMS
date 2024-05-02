package org.reservahoteles.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reservahoteles.dto.MunicipalityDto;
import org.reservahoteles.service.IMunicipalityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/municipalities")
@Log4j2
public class MunicipalityController {

    private final IMunicipalityService iMunicipalityService;


    @GetMapping("/getmunicipalities/all")
    @SecurityRequirement(name = "bearerAuth")
    public List<MunicipalityDto> getListDepartments() {
        return iMunicipalityService.getMunicipalities();
    }

    @GetMapping("/getmunicipalities/department")
    @SecurityRequirement(name = "bearerAuth")
    public List<MunicipalityDto> getListMunicipalitiesByDepartment(@RequestParam Long idDepartment) {
        return iMunicipalityService.getMunicipalitiesByDepartment(idDepartment);
    }

}
