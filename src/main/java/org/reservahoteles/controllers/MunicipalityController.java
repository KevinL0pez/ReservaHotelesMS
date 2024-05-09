package org.reservahoteles.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Response;
import org.reservahoteles.dto.DepartmentDto;
import org.reservahoteles.dto.MunicipalityDto;
import org.reservahoteles.dto.ResponseDto;
import org.reservahoteles.service.IMunicipalityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseDto<List<MunicipalityDto>>> getListDepartments() {

        ResponseDto<List<MunicipalityDto>> responseDto = new ResponseDto<>();
        List<MunicipalityDto> municipalities = iMunicipalityService.getMunicipalities();
        if (municipalities.isEmpty()){
            responseDto.setMessage("Municipalities not found");
            responseDto.setStatusCode(HttpStatus.NOT_FOUND);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }else{
            responseDto.setMessage("Municipalities found successfully");
            responseDto.setStatusCode(HttpStatus.OK);
            responseDto.setData(municipalities);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
    }

    @GetMapping("/getmunicipalities/department")
    public ResponseEntity<ResponseDto<List<MunicipalityDto>>> getListMunicipalitiesByDepartment(@RequestParam Long idDepartment) {

        ResponseDto<List<MunicipalityDto>> responseDto = new ResponseDto<>();
        List<MunicipalityDto> municipalities = iMunicipalityService.getMunicipalitiesByDepartment(idDepartment);
        if (municipalities.isEmpty()){
            responseDto.setMessage("Municipalities not found");
            responseDto.setStatusCode(HttpStatus.NOT_FOUND);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }else{
            responseDto.setMessage("Municipalities found successfully");
            responseDto.setStatusCode(HttpStatus.OK);
            responseDto.setData(municipalities);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
    }

}
