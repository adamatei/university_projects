package fontys.sem3.group.sioux.dalInterfaces;

import fontys.sem3.group.sioux.model.Address;
import fontys.sem3.group.sioux.model.Location;

import java.util.List;

public interface ILocationDal {
    Location getLocationByLocationId (Long locationId);
    List<Location> getAllLocations();
    void addLocation(Location location);
    void deleteById(Long locationId);
    void save(Location location);
}