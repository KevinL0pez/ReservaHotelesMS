package org.reservahoteles.service;

import jakarta.transaction.Transactional;
import org.reservahoteles.dto.DepartmentDto;

import java.util.List;

public interface IDepartmentService {
    @Transactional
    List<DepartmentDto> getDepartments();
}
