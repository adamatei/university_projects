package fontys.sem3.it.ticketstore.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Ticket {

    //unique id to identify the ticket
    @Id
    @GeneratedValue
    private int id;

    //setting up all the details about the ticket
    @OneToOne
    private Concert concert;
    private double price;
    @OneToOne
    private Category category;
    private int quantity;


    //constructors
    public Ticket(int id, Concert concert, double price, Category category,int quantity) {
        this.id = id;
        this.concert = concert;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public Ticket(Concert concert, double price, Category category, int quantity) {
        this.concert = concert;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public Ticket() {
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity;}
}
