package org.reservahoteles.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Municipalities")
public class MunicipalityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_municipality")
    private Long idMunicipality;

    @Column(name = "code_municipality")
    private String codeMunicipality;

    @Column(name = "name_municipality")
    private String nameMunicipality;

    @ManyToOne
    @JoinColumn(name = "id_department")
    private DepartmentEntity department;
}
