package fontys.sem3.it.ticketstore.repository;

import fontys.sem3.it.ticketstore.model.ApiUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ApiUser, Integer> {
    Optional<ApiUser> findByUsername(String username);

    ApiUser getByUsername(String username);
}
