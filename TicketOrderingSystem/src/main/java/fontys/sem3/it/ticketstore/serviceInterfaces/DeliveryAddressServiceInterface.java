package fontys.sem3.it.ticketstore.serviceInterfaces;

import fontys.sem3.it.ticketstore.model.Concert;
import fontys.sem3.it.ticketstore.model.DeliveryAddress;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface DeliveryAddressServiceInterface {

    //getting all the delivery addresses
    CompletableFuture<List<DeliveryAddress>> getDeliveryAddresses();

    //getting one delivery address based on id
    CompletableFuture<DeliveryAddress> getDeliveryAddressById(int id);

    //adding a new delivery address
    CompletableFuture<DeliveryAddress> saveDeliveryAddress(DeliveryAddress address);

    //delete an existing delivery address
    CompletableFuture<Boolean> deleteDeliveryAddress(int id);

    //update an existing delivery address
    CompletableFuture<DeliveryAddress> updateDeliveryAddress(DeliveryAddress address);
}
