package fontys.sem3.it.ticketstore.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class TicketOrder {

    //unique id to identity the order
    @Id
    @GeneratedValue
    private int id;

    //customer's id to identifier the customer who placed the order
    @OneToOne
    private ApiUser apiUser;

    //setting up the details for the order
    @OneToOne
    private DeliveryAddress deliveryAddress;

    private double totalPrice;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Item> items;

    @OneToOne
    private PaymentMethod paymentMethod;

    //constructors
   public TicketOrder(int id, ApiUser apiUser, DeliveryAddress deliveryAddress, double totalPrice, List<Item> items, PaymentMethod paymentMethod) {
        this.id = id;
        this.apiUser = apiUser;
        this.deliveryAddress = deliveryAddress;
        this.totalPrice = totalPrice;
        this.items = items;
        this.paymentMethod = paymentMethod;
    }

    public TicketOrder(ApiUser apiUser, DeliveryAddress deliveryAddress, double totalPrice, List<Item> items, PaymentMethod paymentMethod) {
        this.apiUser = apiUser;
        this.deliveryAddress = deliveryAddress;
        this.totalPrice = totalPrice;
        this.items = items;
        this.paymentMethod = paymentMethod;
    }

    public TicketOrder() {
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ApiUser getApiUser() {
        return apiUser;
    }

    public void setApiUser(ApiUser apiUser) {
        this.apiUser = apiUser;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public PaymentMethod getPaymentMethod() { return paymentMethod; }

    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }

    public double calculateTotalPrice(){
        double p = 0;
        for (Item ticket : items){
            p = p + ticket.getPrice();
        }
        return p;
    }

}
