package org.reservahoteles.jpa.repositories;

import org.reservahoteles.jpa.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

    UserEntity findByNumberDocumentUser(String numberDocumentUser);

    UserEntity findByEmailUser(String emailUser);

}
