package org.reservahoteles.jpa.repositories;

import jakarta.persistence.Query;
import org.reservahoteles.jpa.entities.MunicipalityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MunicipalityRepository extends JpaRepository<MunicipalityEntity, Long> {

    List<MunicipalityEntity> findByDepartmentIdDepartment(Long idDepartment);

}
