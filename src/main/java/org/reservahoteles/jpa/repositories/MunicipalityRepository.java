package org.reservahoteles.jpa.repositories;

import jakarta.persistence.Query;
import org.reservahoteles.jpa.entities.MunicipalityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipalityRepository extends JpaRepository<MunicipalityEntity, Long> { }
