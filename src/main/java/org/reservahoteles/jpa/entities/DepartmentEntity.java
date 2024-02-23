import jakarta.persistence.*;


@Entity
@Table(name = "Departments")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_department;

    @Column(name = "code_department")
    private String code_department;

    @Column(name = "name_department")
    private String name_department;

    @OneToMany(mappedBy = "id_department")
    private List<MunicipalityEntity> municipalities;

    public DepartmentEntity() {
    }

    public DepartmentEntity(Long id_department, String code_department, String name_department) {
        this.id_department = id_department;
        this.code_department = code_department;
        this.name_department = name_department;
    }

    public Long getId_department() {
        return id_department;
    }

    public void setId_department(Long id_department) {
        this.id_department = id_department;
    }

    public String getCode_department() {
        return code_department;
    }

    public void setCode_department(String code_department) {
        this.code_department = code_department;
    }

    public String getName_department() {
        return name_department;
    }

    public void setName_department(String name_department) {
        this.name_department = name_department;
    }
}
