package org.reservahoteles.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reservahoteles.dto.DepartmentDto;
import org.reservahoteles.dto.ResponseDto;
import org.reservahoteles.service.IDepartmentService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
@Log4j2
public class DepartmentController {

    private final IDepartmentService iDepartmentService;

    @CrossOrigin("*")
    @GetMapping("/getdepartments/all")
    public ResponseEntity<ResponseDto<List<DepartmentDto>>> getListDepartments() {

        ResponseDto<List<DepartmentDto>> responseDto = new ResponseDto<>();
        List<DepartmentDto> departments = iDepartmentService.getDepartments();
        if (!departments.isEmpty()) {
            responseDto.setMessage("Departments found successfully");
            responseDto.setStatusCode(HttpStatus.OK);
            responseDto.setData(departments);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } else {
            responseDto.setMessage("Departments not found");
            responseDto.setStatusCode(HttpStatus.NOT_FOUND);
            responseDto.setError(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }
    }
}
