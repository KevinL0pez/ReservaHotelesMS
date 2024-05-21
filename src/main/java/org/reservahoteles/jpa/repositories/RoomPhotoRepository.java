package org.reservahoteles.jpa.repositories;

import org.reservahoteles.jpa.entities.HotelEntity;
import org.reservahoteles.jpa.entities.HotelPhotoEntity;
import org.reservahoteles.jpa.entities.HotelRoomEntity;
import org.reservahoteles.jpa.entities.RoomPhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoomPhotoRepository extends JpaRepository<RoomPhotoEntity, UUID> {
    List<RoomPhotoEntity> findByRoom(HotelRoomEntity hotelRoomEntity);
}
