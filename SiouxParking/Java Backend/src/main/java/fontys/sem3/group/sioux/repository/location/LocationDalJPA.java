package fontys.sem3.group.sioux.repository.location;

import fontys.sem3.group.sioux.dalInterfaces.IAddressDal;
import fontys.sem3.group.sioux.dalInterfaces.ILocationDal;
import fontys.sem3.group.sioux.model.Address;
import fontys.sem3.group.sioux.model.Location;
import fontys.sem3.group.sioux.repository.address.IAddressRepository;
import fontys.sem3.group.sioux.serviceInterfaces.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public class LocationDalJPA implements ILocationDal {

    @Autowired
    ILocationRepository repo;

    @Override
    public Location getLocationByLocationId(Long locationId) {
        return repo.getLocationByLocationId(locationId);
    }

    @Override
    public List<Location> getAllLocations() {
        return repo.findAll();
    }

    @Override
    public void addLocation(Location location) {
        repo.save(location);
    }

    @Override
    public void deleteById(Long locationId) {
        repo.deleteById(locationId);
    }

    @Override
    public void save(Location location) {
        repo.save(location);
    }
}
