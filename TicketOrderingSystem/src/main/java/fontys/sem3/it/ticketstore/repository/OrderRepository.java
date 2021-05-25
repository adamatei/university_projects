package fontys.sem3.it.ticketstore.repository;

import fontys.sem3.it.ticketstore.model.TicketOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<TicketOrder, Integer> {
}
