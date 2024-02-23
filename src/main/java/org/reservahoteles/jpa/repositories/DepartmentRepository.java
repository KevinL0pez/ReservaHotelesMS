


public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long>{
    
    List<DepartmentEntity> findByName_departmentContaining(String name);
    
    List<DepartmentEntity> findAll();

}
