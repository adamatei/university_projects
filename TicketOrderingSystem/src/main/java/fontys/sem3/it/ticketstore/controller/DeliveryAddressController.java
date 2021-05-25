package fontys.sem3.it.ticketstore.controller;

import fontys.sem3.it.ticketstore.model.DeliveryAddress;
import fontys.sem3.it.ticketstore.service.DeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/address")
public class DeliveryAddressController {

    @Autowired
    private DeliveryAddressService service;

    //getting all the available payment methods
    @GetMapping()
    public CompletableFuture<List<DeliveryAddress>> getAllDeliveryAddresses(){
        return service.getDeliveryAddresses();
    }

    //getting a particular payment method based on id
    @GetMapping("{id}")
    //GET at http://localhost:XXXX/payment/3
    public CompletableFuture<DeliveryAddress> getDeliveryAddress(@PathVariable(value = "id") int id) {
        return service.getDeliveryAddressById(id);
    }

    //adding a new payment method to the list
    @PostMapping()
    public CompletableFuture<DeliveryAddress> addDeliveryAddress(@RequestBody DeliveryAddress deliveryAddress){
        return service.saveDeliveryAddress(deliveryAddress);
    }

    //removing a payment method from the list
    @DeleteMapping("{id}")
    //DELETE at http://localhost:XXXX/payment/3
    public CompletableFuture<Boolean> removeDeliveryAddress(@PathVariable(value = "id") int id){
        return service.deleteDeliveryAddress(id);
    }

    //updating a specific payment method
    @PutMapping()
    public CompletableFuture<DeliveryAddress> updateDeliveryAddress(@RequestBody DeliveryAddress deliveryAddress){
        return service.updateDeliveryAddress(deliveryAddress);
    }
}
