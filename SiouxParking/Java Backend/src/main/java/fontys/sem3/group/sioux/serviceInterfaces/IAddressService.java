package fontys.sem3.group.sioux.serviceInterfaces;

import fontys.sem3.group.sioux.model.Address;

import java.util.List;

public interface IAddressService {

    Address getAddressByAddressId (Long addressId);
    List<Address> getAllAddresses();
    void addAddress(Address address);
    void deleteById(Long addressId);
    void save(Address address);
}
