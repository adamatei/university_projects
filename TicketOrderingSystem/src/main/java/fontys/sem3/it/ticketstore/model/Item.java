package fontys.sem3.it.ticketstore.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public class Item {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    private Ticket ticket;

    private int quantity;
    private double price;

    //constructor
    public Item(int id, Ticket ticket, int quantity, double price) {
        this.id = id;
        this.ticket = ticket;
        this.quantity = quantity;
        this.price = price;
    }

    public Item(Ticket ticket, int quantity, double price) {
        this.ticket = ticket;
        this.quantity = quantity;
        this.price = price;
    }

    public Item() {
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ticket getTicket() { return ticket; }

    public void setTicket(Ticket ticket) { this.ticket = ticket; }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double calculatePrice(){
        return quantity * ticket.getPrice();
    }
}
