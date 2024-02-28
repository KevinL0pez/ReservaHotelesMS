import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

    UserEntity findUserEntityByDocument(String number_document_user);
    
}
