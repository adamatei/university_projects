package fontys.sem3.group.sioux.repository.parkingLot;

import fontys.sem3.group.sioux.dalInterfaces.IAddressDal;
import fontys.sem3.group.sioux.dalInterfaces.IParkingLotDal;
import fontys.sem3.group.sioux.model.Address;
import fontys.sem3.group.sioux.model.ParkingLot;
import fontys.sem3.group.sioux.repository.address.IAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParkingLotDalJPA implements IParkingLotDal {

    @Autowired
    IParkingLotRepository repo;

    @Override
    public ParkingLot getParkingLotByParkinglotId(Long parkinglotId) {
        return repo.getParkingLotByParkinglotId(parkinglotId);
    }

    @Override
    public List<ParkingLot> getAllParkingLots() {
        return repo.findAll();
    }

    @Override
    public void addParkingLot(ParkingLot parkingLot) {
        repo.save(parkingLot);
    }

    @Override
    public void deleteById(Long parkinglotId) {
        repo.deleteById(parkinglotId);
    }

    @Override
    public void save(ParkingLot parkingLot) {
        repo.save(parkingLot);
    }
}
