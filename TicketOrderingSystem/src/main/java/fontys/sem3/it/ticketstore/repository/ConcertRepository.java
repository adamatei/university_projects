package fontys.sem3.it.ticketstore.repository;

import fontys.sem3.it.ticketstore.model.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ConcertRepository extends JpaRepository<Concert, Integer> {

}
