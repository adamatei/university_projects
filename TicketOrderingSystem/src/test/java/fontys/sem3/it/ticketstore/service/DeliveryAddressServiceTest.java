package fontys.sem3.it.ticketstore.service;

import fontys.sem3.it.ticketstore.model.DeliveryAddress;
import fontys.sem3.it.ticketstore.repository.DeliveryAddressRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeliveryAddressServiceTest {

    @Mock
    DeliveryAddressRepository repository;

    @InjectMocks
    DeliveryAddressService service;

    @Test
    public void getDeliveryAddresses(){

        List<DeliveryAddress> deliveryAddressList = new ArrayList<>();

        DeliveryAddress da1 = new DeliveryAddress("Tongelresestraat 216", "Netherlands", "Eindhoven", "5631DT");
        DeliveryAddress da2 = new DeliveryAddress("Stratum 23", "Netherlands", "Eindhoven", "3245RT");

        deliveryAddressList.add(da1); deliveryAddressList.add(da2);

        when(repository.findAll()).thenReturn(deliveryAddressList);
        CompletableFuture<List<DeliveryAddress>> completableFuture = service.getDeliveryAddresses();
        List<DeliveryAddress> actualDeliveryAddressList = null;
        try {
            actualDeliveryAddressList = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertArrayEquals(deliveryAddressList.toArray(), actualDeliveryAddressList.toArray());
    }

    @Test
    public void getDeliveryAddressById(){

        DeliveryAddress da1 = new DeliveryAddress(1,"Tongelresestraat 216", "Netherlands", "Eindhoven", "5631DT");

        when(repository.findById(1)).thenReturn(java.util.Optional.of(da1));
        CompletableFuture<DeliveryAddress> completableFuture = service.getDeliveryAddressById(1);
        DeliveryAddress actualDeliveryAddress = null;
        try {
            actualDeliveryAddress = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(da1, actualDeliveryAddress);
    }

    @Test
    public void saveDeliveryAddress(){

        DeliveryAddress da1 = new DeliveryAddress(1,"Tongelresestraat 216", "Netherlands", "Eindhoven", "5631DT");

        when(repository.save(da1)).thenReturn(da1);
        CompletableFuture<DeliveryAddress> completableFuture = service.saveDeliveryAddress(da1);
        DeliveryAddress actualDeliveryAddress = null;
        try {
            actualDeliveryAddress = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(da1, actualDeliveryAddress);
    }

    @Test
    public void updateDeliveryAddress(){

        DeliveryAddress da1 = new DeliveryAddress(1,"Tongelresestraat 216", "Netherlands", "Eindhoven", "5631DT");

        when(repository.findById(1)).thenReturn(java.util.Optional.of(da1));
        when(repository.save(da1)).thenReturn(da1);
        CompletableFuture<DeliveryAddress> completableFuture = service.updateDeliveryAddress(da1);
        DeliveryAddress actualDeliveryAddress = null;
        try {
            actualDeliveryAddress = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(da1, actualDeliveryAddress);
    }
}
