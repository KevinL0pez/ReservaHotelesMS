package org.reservahoteles.dto;


import lombok.Data;
import org.reservahoteles.jpa.entities.DepartmentEntity;

@Data
public class MunicipalityDto {

    private Long idMunicipality;

    private String codeMunicipality;

    private String nameMunicipality;

    private DepartmentEntity department;
}
