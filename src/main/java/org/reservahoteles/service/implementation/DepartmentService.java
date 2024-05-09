package org.reservahoteles.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reservahoteles.dto.DepartmentDto;
import org.reservahoteles.dto.UserDto;
import org.reservahoteles.jpa.repositories.DepartmentRepository;
import org.reservahoteles.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class DepartmentService implements IDepartmentService {
    
    private final DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentDto> getDepartments() {
        return departmentRepository.findAll().stream()
                .map(department -> {
                    DepartmentDto departmentDto = new DepartmentDto();
                    departmentDto.setIdDepartment(department.getIdDepartment());
                    departmentDto.setCodeDepartment(department.getCodeDepartment());
                    departmentDto.setNameDepartment(department.getNameDepartment());
                    departmentDto.setMunicipalities(department.getMunicipalities());
                    return departmentDto;
                })
                .toList();
    }

}
