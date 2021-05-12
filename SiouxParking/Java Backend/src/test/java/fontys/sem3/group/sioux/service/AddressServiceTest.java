package fontys.sem3.group.sioux.service;

import fontys.sem3.group.sioux.dalInterfaces.IAddressDal;
import fontys.sem3.group.sioux.model.Address;
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
public class AddressServiceTest {

    @InjectMocks
    AddressService service;

    @Mock
    IAddressDal repository;

    @Test
    void getAllAddressesTest(){

        List<Address> addresses = new ArrayList<>();

        Address address1 = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");
        Address address2 = new Address(222L,"Niewstraat2","222", "23", "5358KR", "Eindhoven", "Netherlands", "Netherlands");

        addresses.add(address1);
        addresses.add(address2);

        when(repository.getAllAddresses()).thenReturn(addresses);
        List<Address> retrievedAddresses = service.getAllAddresses();

        assertArrayEquals(addresses.toArray(),retrievedAddresses.toArray());
    }

    @Test
    void getAddressByAddressIdTest(){

        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");

        when(repository.getAddressByAddressId(111L)).thenReturn(address);
        Address retrievedAddress = service.getAddressByAddressId(111L);

        assertEquals(address,retrievedAddress);
    }

    @Test
    void addAddressTest(){

        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);

            assertEquals(address, arg0);
            return null;
        }).when(repository).addAddress(any(Address.class));
        service.addAddress(address);
    }

    @Test
    void deleteByIdTest(){

        Address address = new Address(111L,"Niewstraat","211", "22", "5644KC", "Eindhoven", "Netherlands", "Netherlands");
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
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);

            assertEquals(address, arg0);
            return null;
        }).when(repository).save(any(Address.class));
        service.save(address);
    }
}

