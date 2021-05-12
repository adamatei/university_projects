package fontys.sem3.group.sioux.service;

import fontys.sem3.group.sioux.dalInterfaces.IParkingLotDal;
import fontys.sem3.group.sioux.model.Address;
import fontys.sem3.group.sioux.model.Location;
import fontys.sem3.group.sioux.model.ParkingLot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class)
public class ParkingLotServiceTest {

    @InjectMocks
    ParkingLotService service;

    @Mock
    IParkingLotDal repository;

    @Test
    void getAllParkingLotsTest(){

        List<ParkingLot> parkingLots = new ArrayList<>();

        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");
        Location location = new Location(111L, "locationName", address, null);
        ParkingLot parkingLot = new ParkingLot(111L,"Main",300,100,address,location);

        parkingLots.add(parkingLot);

        when(repository.getAllParkingLots()).thenReturn(parkingLots);
        List<ParkingLot> retrievedParkingLots = service.getAllParkingLots();

        assertArrayEquals(parkingLots.toArray(),retrievedParkingLots.toArray());
    }


    @Test
    void getParkingLotByParkinglotIdTest(){

        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");
        Location location = new Location(111L, "locationName", address, null);
        ParkingLot parkingLot = new ParkingLot(111L,"Main",300,100,address,location);

        when(repository.getParkingLotByParkinglotId(111L)).thenReturn(parkingLot);
        ParkingLot retrievedParkingLot = service.getParkingLotByParkinglotId(111L);

        assertEquals(parkingLot,retrievedParkingLot);
    }

    @Test
    void addParkingLotTest(){

        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");
        Location location = new Location(111L, "locationName", address, null);
        ParkingLot parkingLot = new ParkingLot(111L,"Main",300,100,address,location);
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);

            assertEquals(parkingLot, arg0);
            return null;
        }).when(repository).addParkingLot(any(ParkingLot.class));
        service.addParkingLot(parkingLot);
    }


    @Test
    void deleteByIdTest(){

        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");
        Location location = new Location(111L, "locationName", address,null);
        ParkingLot parkingLot = new ParkingLot(111L,"Main",300,100,address,location);
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);

            assertEquals(111L, arg0);
            return null;
        }).when(repository).deleteById(any(Long.class));
        service.deleteById(111L);
    }


    @Test
    void saveTest(){

        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");
        Location location = new Location(111L, "locationName", address,null);
        ParkingLot parkingLot = new ParkingLot(111L,"Main",300,100,address,location);
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);

            assertEquals(parkingLot, arg0);
            return null;
        }).when(repository).save(any(ParkingLot.class));
        service.save(parkingLot);
    }
}

