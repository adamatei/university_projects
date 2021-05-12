package fontys.sem3.group.sioux.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationTest {

    //constructor
    @Test
    void locationConstructorTest(){

        //ARRANGE section
        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");
        Location location = new Location(111L, "locationName", address, new ArrayList<ParkingLot>());

        //ACT section
        Long expectedId = 111L;
        String expectedName = "locationName";
        Address expectedAddress = address;
        List<ParkingLot> expectedParkingLots = new ArrayList<>();

        //ASSERT section
        assertEquals(expectedId,location.getLocationId());
        assertEquals(expectedName,location.getName());
        assertEquals(expectedAddress,location.getAddress());
        assertArrayEquals(new ArrayList<ParkingLot>().toArray(),location.getParkingLots().toArray());
    }

    //setter and getter for 'name'
    @Test
    void nameSetterGetterTest(){

        //ARRANGE section
        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");
        Location location = new Location(111L, "locationName", address, new ArrayList<ParkingLot>());

        //ACT section
        location.setName("newLocationName");
        String expectedName = "newLocationName";

        //ASSERT section
        assertEquals(expectedName,location.getName());
    }

    //setter and getter for 'address'
    @Test
    void addressSetterGetterTest(){

        //ARRANGE section
        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");
        Location location = new Location(111L, "locationName", address, new ArrayList<ParkingLot>());

        //ACT section
        Address newAddress = new Address(112L,"NewStreet","211", "22", "5644KC", "newCity", "newState", "newCountry");
        location.setAddress(newAddress);
        Address expectedAddress = newAddress;

        //ASSERT section
        assertEquals(expectedAddress,location.getAddress());
    }

    //setter and getter for 'parking lots'
    @Test
    void parkingLotSetterGetterTest(){

        //ARRANGE section
        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");
        Location location = new Location(111L, "locationName", address, new ArrayList<ParkingLot>());

        //ACT section
        List<ParkingLot> newParkingLots = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(111L,"Main",300,100,address,location);
        newParkingLots.add(parkingLot);
        location.setParkingLots(newParkingLots);
        List<ParkingLot> expectedParkingLots = newParkingLots;

        //ASSERT section
        assertArrayEquals(expectedParkingLots.toArray(),location.getParkingLots().toArray());
    }
}
