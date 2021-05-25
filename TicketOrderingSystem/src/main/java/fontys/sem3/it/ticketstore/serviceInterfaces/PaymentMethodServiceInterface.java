package fontys.sem3.it.ticketstore.serviceInterfaces;

import fontys.sem3.it.ticketstore.model.PaymentMethod;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PaymentMethodServiceInterface {

    //getting all the payment methods
    CompletableFuture<List<PaymentMethod>> getPaymentMethods();

    //getting one payment method based on id
    CompletableFuture<PaymentMethod> getPaymentMethodById(int id);

    //adding a new payment method
    CompletableFuture<PaymentMethod>  savePaymentMethod(PaymentMethod paymentMethod);

    //delete an existing payment method
    CompletableFuture<Boolean>  deletePaymentMethod(int id);

    //update an existing payment method
    CompletableFuture<PaymentMethod>  updatePaymentMethod(PaymentMethod paymentMethod);
}
