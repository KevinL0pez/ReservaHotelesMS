package org.reservahoteles.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reservahoteles.dto.DepartmentDto;
import org.reservahoteles.service.IDepartmentService;
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


    @GetMapping("/getdepartments/all")
    @SecurityRequirement(name = "bearerAuth")
    public List<DepartmentDto> getListDepartments() {
        return iDepartmentService.getDepartments();
    }



}
