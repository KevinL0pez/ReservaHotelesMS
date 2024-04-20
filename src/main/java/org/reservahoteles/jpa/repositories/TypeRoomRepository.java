package org.reservahoteles.jpa.repositories;

import org.reservahoteles.jpa.entities.TypeRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRoomRepository extends JpaRepository<TypeRoomEntity, Long>{

}
