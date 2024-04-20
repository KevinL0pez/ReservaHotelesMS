package org.reservahoteles.jpa.repositories;

import org.reservahoteles.jpa.entities.DepartmentEntity;
import org.reservahoteles.jpa.entities.HotelEntity;
import org.reservahoteles.jpa.entities.MunicipalityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {

    List<HotelEntity> findByDepartment(DepartmentEntity department);

    List<HotelEntity> findByMunicipality(MunicipalityEntity municipality);


    HotelEntity findByNameHotelAndAddressHotelAndMunicipality(String nameHotel, String addressHotel, MunicipalityEntity municipality);


}
