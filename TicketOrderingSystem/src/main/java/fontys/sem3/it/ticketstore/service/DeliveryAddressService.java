package fontys.sem3.it.ticketstore.service;

import fontys.sem3.it.ticketstore.model.DeliveryAddress;
import fontys.sem3.it.ticketstore.repository.DeliveryAddressRepository;
import fontys.sem3.it.ticketstore.serviceInterfaces.DeliveryAddressServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class DeliveryAddressService implements DeliveryAddressServiceInterface {

    @Autowired
    private DeliveryAddressRepository repository;

    //getting all the delivery addresses
    @Async
    public CompletableFuture<List<DeliveryAddress>> getDeliveryAddresses(){
        return CompletableFuture.completedFuture(repository.findAll());
    }

    //getting one delivery address based on id
    @Async
    public CompletableFuture<DeliveryAddress> getDeliveryAddressById(int id){
        return CompletableFuture.completedFuture(repository.findById(id).orElse(null));
    }

    //adding a new delivery address
    @Async
    public CompletableFuture<DeliveryAddress> saveDeliveryAddress(DeliveryAddress address){
        return CompletableFuture.completedFuture(repository.save(address));
    }

    //delete an existing delivery address
    @Async
    public CompletableFuture<Boolean> deleteDeliveryAddress(int id){
        repository.deleteById(id);
        return CompletableFuture.completedFuture(true);
    }

    //update an existing delivery address
    @Async
    public CompletableFuture<DeliveryAddress> updateDeliveryAddress(DeliveryAddress address){
        DeliveryAddress existingDeliveryAddress = repository.findById(address.getId()).orElse(null);
        existingDeliveryAddress.setStreet(address.getStreet());
        existingDeliveryAddress.setCity(address.getCity());
        existingDeliveryAddress.setCountry(address.getCountry());
        existingDeliveryAddress.setPostcode(address.getPostcode());
        return CompletableFuture.completedFuture(repository.save(existingDeliveryAddress));
    }
}
