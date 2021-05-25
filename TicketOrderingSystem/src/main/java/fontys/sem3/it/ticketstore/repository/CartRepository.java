package fontys.sem3.it.ticketstore.repository;

import fontys.sem3.it.ticketstore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
