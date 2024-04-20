package org.reservahoteles.jpa.repositories;

import org.reservahoteles.jpa.entities.HotelEntity;
import org.reservahoteles.jpa.entities.HotelRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRoomRepository extends JpaRepository<HotelRoomEntity, Long> {

    List<HotelRoomEntity> findByHotel(HotelEntity hotelEntity);
}
