package fontys.sem3.it.ticketstore.repository;

import fontys.sem3.it.ticketstore.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
}
