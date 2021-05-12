package fontys.sem3.group.sioux.service;

import fontys.sem3.group.sioux.dalInterfaces.ILocationDal;
import fontys.sem3.group.sioux.model.Address;
import fontys.sem3.group.sioux.model.Location;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class)
public class LocationServiceTest {

    @InjectMocks
    LocationService service;

    @Mock
    ILocationDal repository;

    @Test
    void getAllLocationsTest(){

        List<Location> locations = new ArrayList<>();

        Address address1 = new Address(111L,"Niewstraat1","211", "21", "5237KC", "Eindhoven", "Netherlands", "Netherlands");
        Location location1 = new Location(111L, "locationName", address1,null);

        Address address2 = new Address(222L,"Niewstraat2","212", "22", "6644RC", "Eindhoven", "Netherlands", "Netherlands");
        Location location2 = new Location(222L, "locationName", address2,null);

        locations.add(location1);
        locations.add(location2);

        when(repository.getAllLocations()).thenReturn(locations);
        List<Location> retrievedLocations = service.getAllLocations();

        assertArrayEquals(locations.toArray(),retrievedLocations.toArray());
    }

    @Test
    void getLocationByLocationIdTest(){

        Address address = new Address(111L,"Niewstraat1","211", "21", "5237KC", "Eindhoven", "Netherlands", "Netherlands");
        Location location = new Location(111L, "locationName", address,null);

        when(repository.getLocationByLocationId(111L)).thenReturn(location);
        Location retrievedLocation = service.getLocationByLocationId(111L);

        assertEquals(location,retrievedLocation);
    }

    @Test
    void addLocationTest(){

        Address address = new Address(111L,"Niewstraat1","211", "21", "5237KC", "Eindhoven", "Netherlands", "Netherlands");
        Location location = new Location(111L, "locationName", address, null);
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);

            assertEquals(location, arg0);
            return null;
        }).when(repository).addLocation(any(Location.class));
        service.addLocation(location);
    }

    @Test
    void deleteByIdTest(){

        Address address = new Address(111L,"Niewstraat1","211", "21", "5237KC", "Eindhoven", "Netherlands", "Netherlands");
        Location location = new Location(111L, "locationName", address,null);
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);

            assertEquals(111L, arg0);
            return null;
        }).when(repository).deleteById(any(Long.class));
        service.deleteById(111L);
    }

    @Test
    void saveTest(){

        Address address = new Address(111L,"Niewstraat1","211", "21", "5237KC", "Eindhoven", "Netherlands", "Netherlands");
        Location location = new Location(111L, "locationName", address,null);
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);

            assertEquals(location, arg0);
            return null;
        }).when(repository).save(any(Location.class));
        service.save(location);
    }
}

