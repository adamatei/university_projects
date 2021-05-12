package fontys.sem3.group.sioux.dalInterfaces;

import fontys.sem3.group.sioux.model.Address;
import fontys.sem3.group.sioux.model.ParkingLot;

import java.util.List;

public interface IParkingLotDal {
    ParkingLot getParkingLotByParkinglotId (Long parkinglotId);
    List<ParkingLot> getAllParkingLots();
    void addParkingLot(ParkingLot parkingLot);
    void deleteById(Long parkinglotId);
    void save(ParkingLot parkingLot);
}
