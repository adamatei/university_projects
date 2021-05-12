package fontys.sem3.group.sioux.controller;

import fontys.sem3.group.sioux.model.Address;
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

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/parkinglot")
public class ParkingLotController {

    @Autowired
    private IParkingLotService parkinglotService;

    @Autowired
    private IAddressService addressService;

    @Autowired
    private ILocationService locationService;

    //GET all the parkinglots from the database
    @GetMapping()
    //GET at http://localhost:XXXX/parkinglot
    public ResponseEntity<Iterable<ParkingLot>> getAllParkingLots(){
        var parkingLots = parkinglotService.getAllParkingLots();
        if (parkingLots != null) {
            return ResponseEntity.ok().body(parkingLots);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //GET parkinglot by id
    @GetMapping("{id}")
    //GET at http://localhost:XXXX/parkinglot/3
    public ResponseEntity<ParkingLot> getParkingLotById(@PathVariable(value = "id") Long id) {
        var parkinglot = parkinglotService.getParkingLotByParkinglotId(id);
        if(parkinglot != null){
            return ResponseEntity.ok().body(parkinglot);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    //ADD a parkinglot - initially address null and location not linked
    @PostMapping()
    //POST at http://localhost:XXXX/parkinglot/
    public ResponseEntity<ParkingLot> createParkingLot(@RequestBody ParkingLot parkinglot) {
        parkinglotService.addParkingLot(parkinglot);
        //Create resource location
        var result = parkinglotService.getParkingLotByParkinglotId(parkinglot.getParkinglotId()).getParkinglotId();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result)
                .toUri();

        //Send location in response (in the header)
        return ResponseEntity.created(location).build();
    }

    //UPDATE parkinglot address
    @PutMapping("/{parkinglotId}/address/{addressId}")
    //PUT at http://localhost:XXXX/parkinglot/3/address/1
    public ResponseEntity<ParkingLot> addAddressToParkingLot(@PathVariable Long parkinglotId, @PathVariable Long addressId){

        ParkingLot parkinglot = parkinglotService.getParkingLotByParkinglotId(parkinglotId);
        Address address = addressService.getAddressByAddressId(addressId);

        parkinglot.setAddress(address);
        parkinglotService.save(parkinglot);

        if(parkinglot != null) {
            return ResponseEntity.ok().body(parkinglot);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //increase parkinglot currentCapacity - visitor has left
    @PutMapping("/{parkinglotId}/increaseCurrentCapacity")
    //PUT at http://localhost:XXXX/parkinglot/3/increaseCurrentCapacity
    public ResponseEntity<ParkingLot> increaseCurrentCapacityOfParkingLot(@PathVariable Long parkinglotId){

        ParkingLot parkinglot = parkinglotService.getParkingLotByParkinglotId(parkinglotId);
        int currentCapacity = parkinglot.getCurrentCapacity();
        currentCapacity = currentCapacity + 1;
        parkinglot.setCurrentCapacity(currentCapacity);
        parkinglotService.save(parkinglot);

        if(parkinglot != null) {
            return ResponseEntity.ok().body(parkinglot);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //decrease parkinglot currentCapacity - visitor has arrived
    @PutMapping("/{parkinglotId}/decreaseCurrentCapacity")
    //PUT at http://localhost:XXXX/parkinglot/3/decreaseCurrentCapacity
    public ResponseEntity<ParkingLot> decreaseCurrentCapacityOfParkingLot(@PathVariable Long parkinglotId){

        ParkingLot parkinglot = parkinglotService.getParkingLotByParkinglotId(parkinglotId);
        int currentCapacity = parkinglot.getCurrentCapacity();
        currentCapacity = currentCapacity - 1;
        parkinglot.setCurrentCapacity(currentCapacity);
        parkinglotService.save(parkinglot);

        if(parkinglot != null) {
            return ResponseEntity.ok().body(parkinglot);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //UPDATE parkinglot
    @PutMapping("{id}")
    //PUT at http://localhost:XXXX/parkinglot/3
    public ResponseEntity<ParkingLot> updateParkingLot(@PathVariable(value = "id") Long id, @RequestBody ParkingLot parkingLot){
        ParkingLot parkingLotData = parkinglotService.getParkingLotByParkinglotId(id);

        if (parkingLotData != null) {
            ParkingLot updatedParkingLot = parkingLotData;
            updatedParkingLot.setParkinglotName(parkingLot.getParkinglotName());
            updatedParkingLot.setCurrentCapacity(parkingLot.getCurrentCapacity());
            updatedParkingLot.setMaxCapacity(parkingLot.getMaxCapacity());
            parkinglotService.save(updatedParkingLot);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //DELETE visitor
    @DeleteMapping("{id}")
    //DELETE at http://localhost:XXXX/addresse/3
    public ResponseEntity<HttpStatus> removeParkingLot(@PathVariable(value = "id") Long id){
        try {
            parkinglotService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
