package org.reservahoteles.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    //JsonIgnore is required, to avoid infinite recursion.
    @JsonIgnore
    private DepartmentEntity department;
}
