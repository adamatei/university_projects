package fontys.sem3.group.sioux.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VisitorTest {

    //constructor without appointments
    @Test
    void visitorContructorWithoutAppointmentsTest(){

        //ARRANGE section
        Visitor visitor = new Visitor("Axl", "Rose", "J206NT", "+31093382637", false);

        //ACT section
        String expectedFirstName = "Axl";
        String expectedLastName = "Rose";
        String expectedLicensePlate = "J206NT";
        String expectedPhone = "+31093382637";
        Boolean expectedIsDeleted = false;

        //ASSERT section
        assertEquals(expectedFirstName,visitor.getFirstName());
        assertEquals(expectedLastName,visitor.getLastName());
        assertEquals(expectedLicensePlate,visitor.getLicencePlate());
        assertEquals(expectedPhone,visitor.getPhone());
        assertEquals(expectedIsDeleted,visitor.getDeleted());
        assertArrayEquals(new ArrayList<Visitor>().toArray(),visitor.getAppointments().toArray());
    }

    //constructor with appointments
    @Test
    void visitorContructorWithAppointmentsTest(){

        //ARRANGE section
        List<Appointment> appointments = new ArrayList<>();
        Appointment appointment = new Appointment(1111111L, "Client Meeting", "Short feedback session with the client", new Location(11111L, "Location1", new Address(111L,"Street","222","21", "P5429DW", "Eindhoven", "Netherlands", "Netherlands"), new ArrayList<ParkingLot>()), new Date(),new ArrayList<Visitor>(), new ArrayList<Employee>());
        appointments.add(appointment);
        Visitor visitor = new Visitor(1111111L,"Axl", "Rose", "J206NT", "+31093382637", false, appointments);

        //ACT section
        Long expectedId = 1111111L;
        String expectedFirstName = "Axl";
        String expectedLastName = "Rose";
        String expectedLicensePlate = "J206NT";
        String expectedPhone = "+31093382637";
        Boolean expectedIsDeleted = false;

        //ASSERT section
        assertEquals(expectedId,visitor.getId());
        assertEquals(expectedFirstName,visitor.getFirstName());
        assertEquals(expectedLastName,visitor.getLastName());
        assertEquals(expectedLicensePlate,visitor.getLicencePlate());
        assertEquals(expectedPhone,visitor.getPhone());
        assertEquals(expectedIsDeleted,visitor.getDeleted());
        assertArrayEquals(appointments.toArray(),visitor.getAppointments().toArray());
    }

    // getter and setter for 'first name'
    @Test
    void firstNameGetterSetterTest(){

        //ARRANGE section
        Visitor visitor = new Visitor("Axl", "Rose", "J206NT", "+31093382637", false);

        //ACT section
        visitor.setFirstName("Steven");
        String expectedFirstName = "Steven";

        //ASSERT section
        assertEquals(expectedFirstName,visitor.getFirstName());
    }

    // getter and setter for 'last name'
    @Test
    void lastNameGetterSetterTest(){

        //ARRANGE section
        Visitor visitor = new Visitor("Axl", "Rose", "J206NT", "+31093382637", false);

        //ACT section
        visitor.setLastName("Tyler");
        String expectedLastName = "Tyler";

        //ASSERT section
        assertEquals(expectedLastName,visitor.getLastName());
    }

    // getter and setter for 'license plate'
    @Test
    void licensePlateGetterSetterTest(){

        //ARRANGE section
        Visitor visitor = new Visitor("Axl", "Rose", "J206NT", "+31093382637", false);

        //ACT section
        visitor.setLicencePlate("J3002NT");
        String expectedLicensePlate = "J3002NT";

        //ASSERT section
        assertEquals(expectedLicensePlate,visitor.getLicencePlate());
    }

    // getter and setter for 'phone'
    @Test
    void phoneGetterSetterTest(){

        //ARRANGE section
        Visitor visitor = new Visitor("Axl", "Rose", "J206NT", "+31093382637", false);

        //ACT section
        visitor.setPhone("+31110082358");
        String expectedPhone = "+31110082358";

        //ASSERT section
        assertEquals(expectedPhone,visitor.getPhone());
    }

    // getter and setter for 'isDeleted'
    @Test
    void isDeletedGetterSetterTest(){

        //ARRANGE section
        Visitor visitor = new Visitor("Axl", "Rose", "J206NT", "+31093382637", false);

        //ACT section
        visitor.setDeleted(true);

        //ASSERT section
        assertTrue(visitor.getDeleted());
    }


    // getter and setter for 'appointments'
    @Test
    void appointmentsGetterSetterTest(){

        //ARRANGE section
        List<Appointment> appointments = new ArrayList<>();
        Appointment appointment = new Appointment(1111111L, "Client Meeting", "Short feedback session with the client", new Location(11111L, "Location1", new Address(111L,"Street","222","21", "P5429DW", "Eindhoven", "Netherlands", "Netherlands"), new ArrayList<ParkingLot>()), new Date(),new ArrayList<Visitor>(), new ArrayList<Employee>());
        appointments.add(appointment);
        Visitor visitor = new Visitor(1111111L,"Axl", "Rose", "J206NT", "+31093382637", false, appointments);

        //ACT section
        visitor.setAppointments(new ArrayList<Appointment>());

        //ASSERT section
        assertArrayEquals(new ArrayList<Appointment>().toArray(),visitor.getAppointments().toArray());
    }

    // remove appointments
    @Test
    void removeAppointmentsTest(){

        //ARRANGE section
        List<Appointment> appointments = new ArrayList<>();
        Appointment appointment = new Appointment(1111111L, "Client Meeting", "Short feedback session with the client", new Location(11111L, "Location1", new Address(111L,"Street","222","21", "P5429DW", "Eindhoven", "Netherlands", "Netherlands"), new ArrayList<ParkingLot>()), new Date(),new ArrayList<Visitor>(), new ArrayList<Employee>());
        appointments.add(appointment);
        Visitor visitor = new Visitor(1111111L,"Axl", "Rose", "J206NT", "+31093382637", false, appointments);

        //ACT section
        visitor.removeAppointment(appointment);

        //ASSERT section
        assertArrayEquals(new ArrayList<Appointment>().toArray(),visitor.getAppointments().toArray());
    }
}
