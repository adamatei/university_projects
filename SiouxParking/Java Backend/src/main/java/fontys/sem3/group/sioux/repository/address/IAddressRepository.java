package fontys.sem3.group.sioux.repository.address;

import fontys.sem3.group.sioux.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<Address, Long> {
    Address getAddressByAddressId(Long addressId);
    void deleteByAddressId(Long addressId);
}
