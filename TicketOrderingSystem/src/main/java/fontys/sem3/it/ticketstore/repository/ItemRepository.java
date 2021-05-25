package fontys.sem3.it.ticketstore.repository;

import fontys.sem3.it.ticketstore.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
