package fontys.sem3.it.ticketstore.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class DeliveryAddress {

    //unique identifier
    @Id
    @GeneratedValue
    private int id;

    //specific details
    private String street;
    private String country;
    private String city;
    private String postcode;

    //constructor
    public DeliveryAddress(String street, String country, String city, String postcode) {
        this.street = street;
        this.country = country;
        this.city = city;
        this.postcode = postcode;
    }

    public DeliveryAddress(int id, String street, String country, String city, String postcode) {
        this.id = id;
        this.street = street;
        this.country = country;
        this.city = city;
        this.postcode = postcode;
    }

    public DeliveryAddress() {
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
