package fontys.sem3.it.ticketstore.repository;

import fontys.sem3.it.ticketstore.model.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Integer> {
}
