package fontys.sem3.it.ticketstore.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class PaymentMethod {

    //unique id to identify a specific method
    @Id
    @GeneratedValue
    private int id;

    //name of the payment method
    private String method;

    //constructor
    public PaymentMethod(int id, String method) {
        this.id = id;
        this.method = method;
    }

    public PaymentMethod() {
    }

    public PaymentMethod(String method) {
        this.method = method;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}
