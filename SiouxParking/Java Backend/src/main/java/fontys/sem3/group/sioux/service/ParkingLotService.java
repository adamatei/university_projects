package fontys.sem3.group.sioux.service;

import fontys.sem3.group.sioux.dalInterfaces.IAddressDal;
import fontys.sem3.group.sioux.dalInterfaces.IParkingLotDal;
import fontys.sem3.group.sioux.model.Address;
import fontys.sem3.group.sioux.model.ParkingLot;
import fontys.sem3.group.sioux.serviceInterfaces.IAddressService;
import fontys.sem3.group.sioux.serviceInterfaces.IParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService implements IParkingLotService {

    IParkingLotDal dal;
    @Autowired
    public ParkingLotService(IParkingLotDal dal)
    {
        this.dal =dal;
    }

    @Override
    public ParkingLot getParkingLotByParkinglotId(Long parkingLotId) {
        return dal.getParkingLotByParkinglotId(parkingLotId);
    }

//    @Override
//    public Address getFirstVisitorByLicencePlate(String licencePlate) {
//        return dal.getFirstAddressByLicencePlate(licencePlate);
//    }

    @Override
    public List<ParkingLot> getAllParkingLots() { return dal.getAllParkingLots(); }

    @Override
    public void addParkingLot(ParkingLot parkingLot) {
        dal.addParkingLot(parkingLot);
    }

    @Override
    public void deleteById(Long parkingLotId) { dal.deleteById(parkingLotId); }

    @Override
    public void save(ParkingLot parkingLot) {
        dal.save(parkingLot);
    }
}