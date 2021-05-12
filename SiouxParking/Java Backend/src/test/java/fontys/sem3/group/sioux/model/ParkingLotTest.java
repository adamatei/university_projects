package fontys.sem3.group.sioux.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingLotTest {

    //constructor
    @Test
    void parkingLotConstructorTest(){

        //ARRANGE section
        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");
        Location location = new Location(111L, "locationName", address, new ArrayList<ParkingLot>());
        ParkingLot parkingLot = new ParkingLot(111L,"Main",300,100,address,location);

        //ACT section
        Long expectedId = 111L;
        String expectedName = "Main";
        int expectedMAxCapacity = 300;
        int expectedCurrentCapacity = 100;
        Location expectedLocation = location;

        //ASSERT section
        assertEquals(expectedId,parkingLot.getParkinglotId());
        assertEquals(expectedName,parkingLot.getParkinglotName());
        assertEquals(expectedMAxCapacity,parkingLot.getMaxCapacity());
        assertEquals(expectedCurrentCapacity,parkingLot.getCurrentCapacity());
        assertEquals(expectedLocation,parkingLot.getLocation());
    }

    //setter and getter for 'name'
    @Test
    void nameSetterGetterTest(){

        //ARRANGE section
        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");
        Location location = new Location(111L, "locationName", address, new ArrayList<ParkingLot>());
        ParkingLot parkingLot = new ParkingLot(111L,"Main",300,100,address,location);

        //ACT section
        parkingLot.setParkinglotName("Additional");
        String expectedName = "Additional";

        //ASSERT section
        assertEquals(expectedName,parkingLot.getParkinglotName());
    }

    //setter and getter for 'maximum capacity'
    @Test
    void maxCapacitySetterGetterTest(){

        //ARRANGE section
        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");
        Location location = new Location(111L, "locationName", address, new ArrayList<ParkingLot>());
        ParkingLot parkingLot = new ParkingLot(111L,"Main",300,100,address,location);

        //ACT section
        parkingLot.setMaxCapacity(400);
        int expectedMaxCapacity = 400;

        //ASSERT section
        assertEquals(expectedMaxCapacity,parkingLot.getMaxCapacity());
    }

    //setter and getter for 'current capacity'
    @Test
    void currentCapacitySetterGetterTest(){

        //ARRANGE section
        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");
        Location location = new Location(111L, "locationName", address, new ArrayList<ParkingLot>());
        ParkingLot parkingLot = new ParkingLot(111L,"Main",300,100,address,location);

        //ACT section
        parkingLot.setCurrentCapacity(100);
        int expectedCurrentCapacity = 100;

        //ASSERT section
        assertEquals(expectedCurrentCapacity,parkingLot.getCurrentCapacity());
    }

    //setter and getter for 'address'
    @Test
    void addressSetterGetterTest(){

        //ARRANGE section
        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");
        Location location = new Location(111L, "locationName", address, new ArrayList<ParkingLot>());
        ParkingLot parkingLot = new ParkingLot(111L,"Main",300,100,address,location);

        //ACT section
        Address newAddress = new Address(112L,"NewStreet","211", "22", "5644KC", "newCity", "newState", "newCountry");
        parkingLot.setAddress(newAddress);
        Address expectedAddress = newAddress;

        //ASSERT section
        assertEquals(expectedAddress,parkingLot.getAddress());
    }

    //setter and getter for 'address'
    @Test
    void locationSetterGetterTest(){

        //ARRANGE section
        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");
        Location location = new Location(111L, "locationName", address, new ArrayList<ParkingLot>());
        ParkingLot parkingLot = new ParkingLot(111L,"Main",300,100,address,location);

        //ACT section
        Address newAddress = new Address(112L,"NewStreet","211", "22", "5644KC", "newCity", "newState", "newCountry");
        Location newLocation = new Location(112L, "newLocationName", newAddress, new ArrayList<ParkingLot>());
        parkingLot.setLocation(newLocation);
        Location expectedLocation = newLocation;

        //ASSERT section
        assertEquals(expectedLocation,parkingLot.getLocation());
    }
}
