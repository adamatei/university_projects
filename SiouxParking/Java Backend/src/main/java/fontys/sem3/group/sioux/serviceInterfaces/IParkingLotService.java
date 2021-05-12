package fontys.sem3.group.sioux.serviceInterfaces;

import fontys.sem3.group.sioux.model.Address;
import fontys.sem3.group.sioux.model.ParkingLot;

import java.util.List;

public interface IParkingLotService {

    ParkingLot getParkingLotByParkinglotId (Long parkinglotId);
    List<ParkingLot> getAllParkingLots();
    void addParkingLot(ParkingLot parkingLot);
    void deleteById(Long parkinglotId);
    void save(ParkingLot parkingLot);
}
