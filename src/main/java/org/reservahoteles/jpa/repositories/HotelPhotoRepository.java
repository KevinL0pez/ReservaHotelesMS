package org.reservahoteles.jpa.repositories;

import org.reservahoteles.jpa.entities.HotelEntity;
import org.reservahoteles.jpa.entities.HotelPhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HotelPhotoRepository extends JpaRepository<HotelPhotoEntity, UUID>{
    List<HotelPhotoEntity> findByHotel(HotelEntity hotelEntity);
}