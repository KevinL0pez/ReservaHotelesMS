import jakarta.persistence.Query;

public interface MunicipalityRepository extends JpaRepository<MunicipalityEntity, Long>{

    List<MunicipalityEntity> findAll();

    /* @Query(
        value = "SELECT * FROM Municipalities WHERE name_municipality LIKE %:id_municipality%"
    )
    List<MunicipalityEntity> findByNameMunicipalityContaining(Long id_municipality);
    */
    
    List<MunicipalityEntity> findByNameMunicipalityContaining(String name_municipality);

    List<MunicipalityEntity> findByDepartmentIdDepartment(Long id_department);
    
}
