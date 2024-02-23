import jakarta.persistence.*;


@Entity
@Table(name = "Municipalities")
public class MunicipalityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_municipality;

    @Column(name = "code_municipality")
    private String code_municipality;

    @Column(name = "name_municipality")
    private String name_municipality;

    @ManyToOne
    @JoinColumn(name = "id_department", referencedColumnName = "id_department")
    private DepartmentEntity department;

    public MunicipalityEntity() {
    }

    public MunicipalityEntity(Long id_municipality, String code_municipality, String name_municipality, DepartmentEntity department) {
        this.id_municipality = id_municipality;
        this.code_municipality = code_municipality;
        this.name_municipality = name_municipality;
        this.department = department;
    }

    public Long getId_municipality() {
        return id_municipality;
    }

    public void setId_municipality(Long id_municipality) {
        this.id_municipality = id_municipality;
    }

    public String getCode_municipality() {
        return code_municipality;
    }

    public void setCode_municipality(String code_municipality) {
        this.code_municipality = code_municipality;
    }

    public String getName_municipality() {
        return name_municipality;
    }

    public void setName_municipality(String name_municipality) {
        this.name_municipality = name_municipality;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }
}
