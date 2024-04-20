package org.reservahoteles.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.reservahoteles.jpa.entities.MunicipalityEntity;

import java.util.List;

@Data
public class DepartmentDto {

    private Long idDepartment;

    private String codeDepartment;

    private String nameDepartment;

    private List<MunicipalityEntity> municipalities;
}
