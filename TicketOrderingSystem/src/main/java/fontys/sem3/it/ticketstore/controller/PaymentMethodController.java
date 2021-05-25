package fontys.sem3.it.ticketstore.controller;

import fontys.sem3.it.ticketstore.model.PaymentMethod;
import fontys.sem3.it.ticketstore.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/payment")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService service;

    //getting all the available payment methods
    @GetMapping()
    public CompletableFuture<List<PaymentMethod>> getAllPaymentMethods(){
       return service.getPaymentMethods();
    }

    //getting a particular payment method based on id
    @GetMapping("{id}")
    //GET at http://localhost:XXXX/payment/3
    public CompletableFuture<PaymentMethod>  getPaymentMethod(@PathVariable(value = "id") int id) {
       return service.getPaymentMethodById(id);
    }

    //adding a new payment method to the list
    @PostMapping()
    public CompletableFuture<PaymentMethod>  addPaymentMethod(@RequestBody PaymentMethod paymentMethod){
       return service.savePaymentMethod(paymentMethod);
    }

    //removing a payment method from the list
    @DeleteMapping("{id}")
    //DELETE at http://localhost:XXXX/payment/3
    public CompletableFuture<Boolean>  removePaymentMethod(@PathVariable(value = "id") int id){
        return service.deletePaymentMethod(id);
    }


    //updating a specific payment method
    @PutMapping()
    public CompletableFuture<PaymentMethod>  updatePaymentMethod(@RequestBody PaymentMethod paymentMethod){
        return service.updatePaymentMethod(paymentMethod);
    }
}
