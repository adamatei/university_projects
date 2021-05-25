package fontys.sem3.it.ticketstore.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentMethodTest {

    //constructor test
    @Test
    public void constructorPaymentMethod(){

        //ARRANGE section
        PaymentMethod payment = new PaymentMethod(1, "card");

        //ACT section
        int id = 1;
        String method = "card";

        //ASSERT section
        assertEquals(id,payment.getId());
        assertEquals(method,payment.getMethod());
    }


    //method getter and setter test
    @Test
    public void setGetMethodPaymentMethod(){

        //ARRANGE section
        PaymentMethod payment = new PaymentMethod(1, "card");

        //ACT section
        int id = 1;
        String method = "cash";
        payment.setMethod(method);

        //ASSERT section
        assertEquals(id,payment.getId());
        assertEquals(method,payment.getMethod());
    }
}
