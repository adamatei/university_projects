package fontys.sem3.group.sioux.repository.location;

import fontys.sem3.group.sioux.model.Address;
import fontys.sem3.group.sioux.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILocationRepository extends JpaRepository<Location, Long> {
    Location getLocationByLocationId(Long locationId);
    void deleteByLocationId(Long locationId);
}
