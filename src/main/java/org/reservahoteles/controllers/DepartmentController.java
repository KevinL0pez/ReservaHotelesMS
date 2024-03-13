package org.reservahoteles.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reservahoteles.dto.DepartmentDto;
import org.reservahoteles.dto.UserDto;
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
    public List<DepartmentDto> getListDepartments() {
        return iDepartmentService.getDepartments();
    }



}
