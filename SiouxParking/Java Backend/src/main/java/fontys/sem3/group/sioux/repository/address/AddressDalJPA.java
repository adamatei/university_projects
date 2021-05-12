package fontys.sem3.group.sioux.repository.address;

import fontys.sem3.group.sioux.dalInterfaces.IAddressDal;
import fontys.sem3.group.sioux.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressDalJPA implements IAddressDal {

    @Autowired
    IAddressRepository repo;

    @Override
    public Address getAddressByAddressId(Long addressId) {
        return repo.getAddressByAddressId(addressId);
    }

    @Override
    public List<Address> getAllAddresses() {
        return repo.findAll();
    }

    @Override
    public void addAddress(Address address) {
        repo.save(address);
    }

    @Override
    public void deleteById(Long addressId) {
        repo.deleteById(addressId);
    }

    @Override
    public void save(Address address) {
        repo.save(address);
    }
}
