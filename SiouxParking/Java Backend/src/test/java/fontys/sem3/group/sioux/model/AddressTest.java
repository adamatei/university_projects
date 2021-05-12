package fontys.sem3.group.sioux.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressTest {

    //constructor
    @Test
    void addressConstructorTest(){

        //ARRANGE section
        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");

        //ACT section
        Long expectedID = 111L;
        String expectedStreet = "Niewstraat";
        String expectedNumber = "211";
        String expectedNumberAddition = "22";
        String expectedPostCode = "5644KC";
        String expectedCity = "Eindhoven";
        String expectedState = "Netherlands";
        String expectedCountry = "Netherlands";

        //ASSERT section
        assertEquals(expectedID,address.getAddressId());
        assertEquals(expectedStreet,address.getStreet());
        assertEquals(expectedNumber,address.getNumber());
        assertEquals(expectedNumberAddition,address.getNumberAddition());
        assertEquals(expectedPostCode,address.getPostCode());
        assertEquals(expectedCity,address.getCity());
        assertEquals(expectedState,address.getState());
        assertEquals(expectedCountry,address.getCountry());
    }

    //setter and getter for 'street'
    @Test
    void streetSetterGetterTest(){

        //ARRANGE section
        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");

        //ACT section
        address.setStreet("NewStreet");
        String expectedStreet = "NewStreet";

        //ASSERT section
        assertEquals(expectedStreet,address.getStreet());
    }

    //setter and getter for 'number'
    @Test
    void numberSetterGetterTest(){

        //ARRANGE section
        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");

        //ACT section
        address.setNumber("222");
        String expectedNumber = "222";

        //ASSERT section
        assertEquals(expectedNumber,address.getNumber());
    }

    //setter and getter for 'number addition'
    @Test
    void numberAdditionSetterGetterTest(){

        //ARRANGE section
        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");

        //ACT section
        address.setNumberAddition("222");
        String expectedNumberAddition = "222";

        //ASSERT section
        assertEquals(expectedNumberAddition,address.getNumberAddition());
    }

    //setter and getter for 'post code'
    @Test
    void postCodeSetterGetterTest(){

        //ARRANGE section
        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");

        //ACT section
        address.setPostCode("5744RD");
        String expectedPostCode = "5744RD";

        //ASSERT section
        assertEquals(expectedPostCode,address.getPostCode());
    }

    //setter and getter for 'city'
    @Test
    void citySetterGetterTest(){

        //ARRANGE section
        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");

        //ACT section
        address.setCity("Amsterdam");
        String expectedCity = "Amsterdam";

        //ASSERT section
        assertEquals(expectedCity,address.getCity());
    }

    //setter and getter for 'state'
    @Test
    void stateSetterGetterTest(){

        //ARRANGE section
        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");

        //ACT section
        address.setState("New State");
        String expectedState = "New State";

        //ASSERT section
        assertEquals(expectedState,address.getState());
    }

    //setter and getter for 'country'
    @Test
    void countrySetterGetterTest(){

        //ARRANGE section
        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");

        //ACT section
        address.setCountry("New Country");
        String expectedCountry = "New Country";

        //ASSERT section
        assertEquals(expectedCountry,address.getCountry());
    }
}
