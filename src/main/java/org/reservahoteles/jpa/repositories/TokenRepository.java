package org.reservahoteles.jpa.repositories;

import org.reservahoteles.jpa.entities.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Integer> {


    @Query("select t from TokenEntity t " +
            "inner join UserEntity u on t.user.idUser = u.idUser " +
            "where t.user.idUser = :userId and t.loggedOut = false"
    )
    List<TokenEntity> findAllTokensByUser(Integer userId);

    Optional<TokenEntity> findByToken(String token);
}
