package org.reservahoteles.jpa.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Departments")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_department")
    private Long idDepartment;

    @Column(name = "code_department")
    private String codeDepartment;

    @Column(name = "name_department")
    private String nameDepartment;

    @OneToMany(mappedBy = "department")
    private List<MunicipalityEntity> municipalities;
}
