package fontys.sem3.it.ticketstore.service;

import fontys.sem3.it.ticketstore.model.PaymentMethod;
import fontys.sem3.it.ticketstore.repository.PaymentMethodRepository;
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
public class PaymentMethodServiceTest {

    @Mock
    PaymentMethodRepository repository;

    @InjectMocks
    PaymentMethodService service;

    @Test
    public void getPaymentMethods(){
        List<PaymentMethod> paymentMethods = new ArrayList<>();

        PaymentMethod payment1 = new PaymentMethod(1, "card");
        PaymentMethod payment2 = new PaymentMethod(2, "cash");

        paymentMethods.add(payment1);
        paymentMethods.add(payment2);

        when(repository.findAll()).thenReturn(paymentMethods);
        CompletableFuture<List<PaymentMethod>> completableFuture = service.getPaymentMethods();
        List<PaymentMethod> actualPaymentMethods = null;
        try {
            actualPaymentMethods = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertArrayEquals(paymentMethods.toArray(), actualPaymentMethods.toArray());
    }

    @Test
    public void getPaymentMethodById(){

        PaymentMethod payment1 = new PaymentMethod(1, "card");

        when(repository.findById(1)).thenReturn(java.util.Optional.of(new PaymentMethod(1, "card")));
        CompletableFuture<PaymentMethod> completableFuture = service.getPaymentMethodById(1);
        PaymentMethod actualPaymentMethod = null;
        try {
            actualPaymentMethod = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(payment1, actualPaymentMethod);
    }

    @Test
    public void savePaymentMethod(){

        PaymentMethod payment1 = new PaymentMethod(1, "card");

        when(repository.save(payment1)).thenReturn(payment1);
        CompletableFuture<PaymentMethod> completableFuture = service.savePaymentMethod(payment1);
        PaymentMethod actualPaymentMethod = null;
        try {
            actualPaymentMethod = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(payment1, actualPaymentMethod);
    }

    @Test
    public void updatePaymentMethod(){

        PaymentMethod payment1 = new PaymentMethod(1, "card");

        when(repository.findById(1)).thenReturn(java.util.Optional.of(new PaymentMethod(1, "card")));
        when(repository.save(payment1)).thenReturn(payment1);
        CompletableFuture<PaymentMethod> completableFuture = service.updatePaymentMethod(payment1);
        PaymentMethod actualPaymentMethod = null;
        try {
            actualPaymentMethod = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(payment1, actualPaymentMethod);
    }
}
