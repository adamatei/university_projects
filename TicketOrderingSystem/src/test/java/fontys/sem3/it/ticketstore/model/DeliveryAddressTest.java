package fontys.sem3.it.ticketstore.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryAddressTest {

    //constructor test
    @Test
    public void constructorDeliveryAddress(){

        //ARRANGE section
        DeliveryAddress add = new DeliveryAddress("Tongelresestraat 216", "Netherlands", "Eindhoven", "5631DT");

        //ACT section
        String street = "Tongelresestraat 216";
        String country = "Netherlands";
        String city = "Eindhoven";
        String postcode = "5631DT";

        //ASSERT section
        assertEquals(street,add.getStreet());
        assertEquals(country,add.getCountry());
        assertEquals(city,add.getCity());
        assertEquals(postcode,add.getPostcode());
    }

    //setters and getters test
    @Test
    public void setsGetsDeliveryAddress(){

        //ARRANGE section
        DeliveryAddress add = new DeliveryAddress("Tongelresestraat 216", "Netherlands", "Eindhoven", "5631DT");

        //ACT section
        String street = "Aberstreet 256";
        String country = "United Kingdom";
        String city = "Bristol";
        String postcode = "3325ST";
        add.setStreet(street);
        add.setCountry(country);
        add.setCity(city);
        add.setPostcode(postcode);

        //ASSERT section
        assertEquals(street,add.getStreet());
        assertEquals(country,add.getCountry());
        assertEquals(city,add.getCity());
        assertEquals(postcode,add.getPostcode());
    }

}
