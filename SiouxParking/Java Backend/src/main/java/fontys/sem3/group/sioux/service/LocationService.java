package fontys.sem3.group.sioux.service;

import fontys.sem3.group.sioux.dalInterfaces.ILocationDal;
import fontys.sem3.group.sioux.model.Location;
import fontys.sem3.group.sioux.serviceInterfaces.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService implements ILocationService {

    ILocationDal dal;
    @Autowired
    public LocationService(ILocationDal dal)
    {
        this.dal =dal;
    }

    @Override
    public Location getLocationByLocationId(Long locationId) {
        return dal.getLocationByLocationId(locationId);
    }

    @Override
    public List<Location> getAllLocations() { return dal.getAllLocations(); }

    @Override
    public void addLocation(Location location) {
        dal.addLocation(location);
    }

    @Override
    public void deleteById(Long locationId) { dal.deleteById(locationId); }

    @Override
    public void save(Location location) {
        dal.save(location);
    }
}