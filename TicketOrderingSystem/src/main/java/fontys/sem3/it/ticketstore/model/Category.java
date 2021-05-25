package fontys.sem3.it.ticketstore.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Category {

    //unique id to identify a specific category of tickets
    @Id
    @GeneratedValue
    private int id;

    //setting up the attributes
    private String type;

    //constructors
    public Category(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public Category(String type) {
        this.type = type;
    }

    public Category() {
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
