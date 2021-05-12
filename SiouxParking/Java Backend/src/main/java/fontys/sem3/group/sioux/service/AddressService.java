package fontys.sem3.group.sioux.service;

import fontys.sem3.group.sioux.dalInterfaces.IAddressDal;
import fontys.sem3.group.sioux.dalInterfaces.IVisitorDal;
import fontys.sem3.group.sioux.model.Address;
import fontys.sem3.group.sioux.model.Visitor;
import fontys.sem3.group.sioux.serviceInterfaces.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements IAddressService{

    IAddressDal dal;
    @Autowired
    public AddressService(IAddressDal dal)
    {
        this.dal =dal;
    }

    @Override
    public Address getAddressByAddressId(Long addressId) {
        return dal.getAddressByAddressId(addressId);
    }

    @Override
    public List<Address> getAllAddresses() { return dal.getAllAddresses(); }

    @Override
    public void addAddress(Address address) {
        dal.addAddress(address);
    }

    @Override
    public void deleteById(Long addressId) { dal.deleteById(addressId); }

    @Override
    public void save(Address address) {
        dal.save(address);
    }
}
