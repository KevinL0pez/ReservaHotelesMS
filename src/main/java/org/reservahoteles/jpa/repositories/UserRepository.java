package org.reservahoteles.jpa.repositories;

import org.reservahoteles.jpa.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

    UserEntity findByNumberDocumentUser(String numberDocumentUser);

    @Query(
            value = "SELECT u FROM UserEntity u WHERE u.emailUser = :emailUser"
    )
    UserEntity findEmailUser(@Param("emailUser") String emailUser);

    Optional<UserEntity> findByUsername(String username);

}
