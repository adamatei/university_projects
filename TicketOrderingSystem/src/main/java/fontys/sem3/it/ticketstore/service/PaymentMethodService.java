package fontys.sem3.it.ticketstore.service;

import fontys.sem3.it.ticketstore.model.PaymentMethod;
import fontys.sem3.it.ticketstore.repository.PaymentMethodRepository;
import fontys.sem3.it.ticketstore.serviceInterfaces.PaymentMethodServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PaymentMethodService implements PaymentMethodServiceInterface {

    @Autowired
    private PaymentMethodRepository repository;

    //getting all the payment methods
    @Async
    public CompletableFuture<List<PaymentMethod>> getPaymentMethods(){
        return CompletableFuture.completedFuture(repository.findAll());
    }

    //getting one payment method based on id
    @Async
    public CompletableFuture<PaymentMethod>  getPaymentMethodById(int id){
        return CompletableFuture.completedFuture(repository.findById(id).orElse(null));
    }

    //adding a new payment method
    @Async
    public CompletableFuture<PaymentMethod>  savePaymentMethod(PaymentMethod paymentMethod){
        return CompletableFuture.completedFuture(repository.save(paymentMethod));
    }

    //delete an existing payment method
    @Async
    public CompletableFuture<Boolean>  deletePaymentMethod(int id){
        repository.deleteById(id);
        return CompletableFuture.completedFuture(true);
    }

    //update an existing payment method
    @Async
    public CompletableFuture<PaymentMethod>  updatePaymentMethod(PaymentMethod paymentMethod){
        PaymentMethod existingPaymentMethod = repository.findById(paymentMethod.getId()).orElse(null);
        existingPaymentMethod.setMethod(paymentMethod.getMethod());
        return CompletableFuture.completedFuture(repository.save(existingPaymentMethod));
    }
}
