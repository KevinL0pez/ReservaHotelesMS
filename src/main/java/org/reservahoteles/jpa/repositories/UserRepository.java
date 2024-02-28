package org.reservahoteles.jpa.repositories;

import org.reservahoteles.jpa.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{ }
