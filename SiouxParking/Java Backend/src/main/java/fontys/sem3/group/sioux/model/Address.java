package fontys.sem3.group.sioux.model;

import javax.persistence.*;

@Entity
@Table(name ="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private String number;

    @Column(name = "numberAddition")
    private String numberAddition;

    @Column(name = "postCode")
    private String postCode;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    public Address() {
    }

    public Address(Long addressId, String street, String number, String numberAddition, String postCode, String city, String state, String country) {
        this.addressId = addressId;
        this.street = street;
        this.number = number;
        this.numberAddition = numberAddition;
        this.postCode = postCode;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public Long getAddressId() {
        return addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumberAddition() {
        return numberAddition;
    }

    public void setNumberAddition(String numberAddition) {
        this.numberAddition = numberAddition;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
