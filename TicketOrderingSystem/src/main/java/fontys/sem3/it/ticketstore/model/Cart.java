package fontys.sem3.it.ticketstore.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Cart {

    //unique id to identity the order
    @Id
    @GeneratedValue
    private int id;

    //customer's id to identifier the cart she/he has
    @OneToOne
    private ApiUser apiUser;

    //setting up the details for the cart
    private double totalPrice = 0;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Item> items;

    //constructors
    public Cart(int id, ApiUser apiUser, double totalPrice, List<Item> tickets) {
        this.id = id;
        this.apiUser = apiUser;
        this.totalPrice = totalPrice;
        this.items = tickets;
    }

    public Cart(ApiUser apiUser, double totalPrice, List<Item> tickets) {
        this.apiUser = apiUser;
        this.totalPrice = totalPrice;
        this.items = tickets;
    }

    public Cart(int id, ApiUser apiUser, List<Item> tickets) {
        this.id = id;
        this.apiUser = apiUser;
        this.items = tickets;
    }

    public Cart() {
    }

    // setters and getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ApiUser getApiUser() { return apiUser;}

    public void setApiUser(ApiUser apiUser) { this.apiUser = apiUser; }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> tickets) {
        this.items = tickets;
    }

    public double calculatateTotalPrice(){
        double p = 0;
        for (Item ticket : items){
            p = p + ticket.getPrice();
        }
        return p;
    }

    public Item getItem(int id){
        for (Item ticket : items){
            if(ticket.getId() == id){
                return ticket;
            }
        }
        return null;
    }

    public boolean addItem(Item t){
        for (Item ticket : items){
            if(ticket.getId() == t.getId()){
                return false;
            }
           // if(ticket.getTicket().getId() == t.getTicket().getId() ){
            //    ticket.setQuantity(ticket.getQuantity() + t.getQuantity());
            //    ticket.calculatePrice();
            //    return true;
            //}
        }
        items.add(t);
        return true;
    }

    public boolean deleteItem(int id){
        for (Item ticket : items){
            if(ticket.getId() == id){
                items.remove(ticket);
                return true;
            }
        }
        return false;
    }
}
