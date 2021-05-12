package fontys.sem3.group.sioux.controller;

import fontys.sem3.group.sioux.model.Address;
import fontys.sem3.group.sioux.model.Location;
import fontys.sem3.group.sioux.model.ParkingLot;
import fontys.sem3.group.sioux.serviceInterfaces.IAddressService;
import fontys.sem3.group.sioux.serviceInterfaces.ILocationService;
import fontys.sem3.group.sioux.serviceInterfaces.IParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private ILocationService locationService;

    @Autowired
    private IAddressService addressService;

    @Autowired
    private IParkingLotService parkingLotService;

    //GET all the locations from the database
    @GetMapping()
    //GET at http://localhost:XXXX/location
    public ResponseEntity<Iterable<Location>> getAllLocations(){
        var locations = locationService.getAllLocations();
        if (locations != null) {
            return ResponseEntity.ok().body(locations);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //ADD a location (user inputs address on creation but parking lots are not linked yet)
    @PostMapping()
    //POST at http://localhost:XXXX/location/
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        locationService.addLocation(location);
        //Create resource location
        var result = locationService.getLocationByLocationId(location.getLocationId()).getLocationId();
        URI location1 = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result)
                .toUri();

        //Send location in response (in the header)
        return ResponseEntity.created(location1).build();
    }

    //GET location by id
    @GetMapping("{id}")
    //GET at http://localhost:XXXX/location/3
    public ResponseEntity<Location> getLocationById(@PathVariable(value = "id") Long id) {
        var location = locationService.getLocationByLocationId(id);
        if(location != null){
            return ResponseEntity.ok().body(location);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    //UPDATE location - address and parking lot update trough their own controllers - leave null
    @PutMapping("{id}")
    //PUT at http://localhost:XXXX/location/
    public ResponseEntity<Location> updateLocation(@PathVariable(value = "id") Long id, @RequestBody Location location){
        Location locationData = locationService.getLocationByLocationId(id);

        if (locationData != null) {
            Location updatedLocation = locationData;
            updatedLocation.setName(location.getName());
//            updatedLocation.setAddress(location.getAddress());
//            updatedLocation.setParkingLots(location.getParkingLots());
            locationService.save(updatedLocation);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Add a address to a location
    //To be used when user has list of locations to choose from - not when updating address
    @PutMapping("/{locationId}/address/{addressId}")
    //PUT at http://localhost:XXXX/location
    public ResponseEntity<Location> updateAddressOfLocation(@PathVariable Long locationId, @PathVariable Long addressId)  {
        Location location = locationService.getLocationByLocationId(locationId);
        Address address = addressService.getAddressByAddressId(addressId);
        location.setAddress(address);
        locationService.save(location);

        if(location != null) {
            return ResponseEntity.ok().body(location);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Add a parkinglot to a location
    @PutMapping("/{locationId}/parkinglot/{parkinglotId}")
    //PUT at http://localhost:XXXX/location
    public ResponseEntity<Location> addParkingLotToLocation(@PathVariable Long locationId, @PathVariable Long parkinglotId)  {
        Location location = locationService.getLocationByLocationId(locationId);
        ParkingLot parkinglot = parkingLotService.getParkingLotByParkinglotId(parkinglotId);
        parkinglot.setLocation(location);
        parkingLotService.save(parkinglot);
        location.getParkingLots().add(parkinglot);
        locationService.save(location);

        if(location != null) {
            return ResponseEntity.ok().body(location);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Remove a parkinglot from a location
    @DeleteMapping("/{locationId}/parkinglot/{parkinglotId}")
    //PUT at http://localhost:XXXX/location
    public ResponseEntity<Location> removeParkingLotFromLocation(@PathVariable Long locationId, @PathVariable Long parkinglotId)  {
        Location location = locationService.getLocationByLocationId(locationId);
        ParkingLot parkinglot = parkingLotService.getParkingLotByParkinglotId(parkinglotId);
        location.getParkingLots().remove(parkinglot);
        parkinglot.setLocation(null);
        parkingLotService.save(parkinglot);

        if(location != null) {
            return ResponseEntity.ok().body(location);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //DELETE location
    @DeleteMapping("{id}")
    //DELETE at http://localhost:XXXX/location/3
    public ResponseEntity<HttpStatus> removeLocation(@PathVariable(value = "id") Long id){
        try {
            locationService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
