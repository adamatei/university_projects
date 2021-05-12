package fontys.sem3.group.sioux.controller;

import fontys.sem3.group.sioux.model.Address;
import fontys.sem3.group.sioux.serviceInterfaces.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private IAddressService service;

    //GET all the addresses from the H2 database
    @GetMapping()
    //GET at http://localhost:XXXX/addresses
    public ResponseEntity<Iterable<Address>> getAllAddresses(){
        var addresses = service.getAllAddresses();
        if (addresses != null) {
            return ResponseEntity.ok().body(addresses);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //ADD a visitor
    @PostMapping()
    //POST at http://localhost:XXXX/addresses/
    public ResponseEntity<Address> createVisitor(@RequestBody Address address) {
        service.addAddress(address);
        //Create resource location
        var result = service.getAddressByAddressId(address.getAddressId()).getAddressId();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result)
                .toUri();

        //Send location in response (in the header)
        return ResponseEntity.created(location).build();
    }

    //GET visitor by id
    @GetMapping("{id}")
    //GET at http://localhost:XXXX/addresses/3
    public ResponseEntity<Address> getAddressById(@PathVariable(value = "id") Long id) {
        var address = service.getAddressByAddressId(id);
        if(address != null){
            return ResponseEntity.ok().body(address);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    //UPDATE visitor
    @PutMapping("{id}")
    //PUT at http://localhost:XXXX/addresses/
    public ResponseEntity<Address> updateAddress(@PathVariable(value = "id") Long id, @RequestBody Address address){
        Address addressData = service.getAddressByAddressId(id);

        if (addressData != null) {
            Address updatedAddress = addressData;
            updatedAddress.setCity(address.getCity());
            updatedAddress.setCountry(address.getCountry());
            updatedAddress.setNumber(address.getNumber());
            updatedAddress.setNumberAddition(address.getNumberAddition());
            updatedAddress.setPostCode(address.getPostCode());
            updatedAddress.setState(address.getState());
            updatedAddress.setStreet(address.getStreet());
            service.save(updatedAddress);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //DELETE visitor
    @DeleteMapping("{id}")
    //DELETE at http://localhost:XXXX/addresses/3
    public ResponseEntity<HttpStatus> removeAddress(@PathVariable(value = "id") Long id){
        try {
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
