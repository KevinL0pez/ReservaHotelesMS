package org.reservahoteles.jpa.repositories;

import org.reservahoteles.jpa.entities.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long>{
    List<ReservationEntity> findByStatusReservationIdStatusReservation(Long idStatusReservation);

    List<ReservationEntity> findByUserIdUser(Long idUser);

    List<ReservationEntity> findByUserIdUserAndStatusReservationIdStatusReservation(Long idUser, Long idStatusReservation);

    List<Optional<ReservationEntity>> findByHotelRoomEntityIdHotelRoomAndHotelIdHotelAndStatusReservationIdStatusReservation(Long idHotelRoom, Long idHotel, Long idStatusReservation);

}
