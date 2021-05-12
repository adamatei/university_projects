package fontys.sem3.group.sioux.repository.parkingLot;

import fontys.sem3.group.sioux.model.ParkingLot;
import fontys.sem3.group.sioux.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IParkingLotRepository extends JpaRepository<ParkingLot, Long> {
    ParkingLot getParkingLotByParkinglotId(Long parkinglotId);
    //Visitor getFirstVisitorByLicencePlate(String licencePlate);
    void deleteById(Long parkinglotId);
}
