package fontys.sem3.group.sioux.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    //constructor
    @Test
    void employeeConstructorTest(){

        //ARRANGE section
        List<Appointment> appointments = new ArrayList<>();
        Appointment appointment = new Appointment(1111111L, "Client Meeting", "Short feedback session with the client", new Location(11111L, "Location1", new Address(111L,"Street","222","21", "P5429DW", "Eindhoven", "Netherlands", "Netherlands"), new ArrayList<ParkingLot>()), new Date(),new ArrayList<Visitor>(), new ArrayList<Employee>());
        appointments.add(appointment);
        Employee employee = new Employee(1111111L,"Axl", "Rose", "axl07@gmail.com", "+31093382637", false, appointments);

        //ACT section
        Long expectedId = 1111111L;
        String expectedFirstName = "Axl";
        String expectedLastName = "Rose";
        String expectedEmail = "axl07@gmail.com";
        String expectedPhone = "+31093382637";
        Boolean expectedIsDeleted = false;
        List<Appointment> newAppointments = new ArrayList<>();

        //ASSERT section
        assertEquals(expectedId,employee.getEmployeeId());
        assertEquals(expectedFirstName,employee.getFirstName());
        assertEquals(expectedLastName,employee.getLastName());
        assertEquals(expectedPhone,employee.getPhone());
        assertEquals(expectedEmail,employee.getEmail());
        assertEquals(expectedIsDeleted,employee.getDeleted());
        assertArrayEquals(appointments.toArray(),employee.getAppointments().toArray());
    }

    //setter and getter for 'first name'
    @Test
    void firstNameSetterGetterTest(){

        //ARRANGE section
        List<Appointment> appointments = new ArrayList<>();
        Appointment appointment = new Appointment(1111111L, "Client Meeting", "Short feedback session with the client", new Location(11111L, "Location1", new Address(111L,"Street","222","21", "P5429DW", "Eindhoven", "Netherlands", "Netherlands"), new ArrayList<ParkingLot>()), new Date(),new ArrayList<Visitor>(), new ArrayList<Employee>());
        appointments.add(appointment);
        Employee employee = new Employee(1111111L,"Axl", "Rose", "axl07@gmail.com", "+31093382637", false, appointments);

        //ACT section
        employee.setFirstName("Steven");
        String expectedFirstName = "Steven";

        //ASSERT section
        assertEquals(expectedFirstName,employee.getFirstName());
    }

    //setter and getter for 'last name'
    @Test
    void lastNameSetterGetterTest(){

        //ARRANGE section
        List<Appointment> appointments = new ArrayList<>();
        Appointment appointment = new Appointment(1111111L, "Client Meeting", "Short feedback session with the client", new Location(11111L, "Location1", new Address(111L,"Street","222","21", "P5429DW", "Eindhoven", "Netherlands", "Netherlands"), new ArrayList<ParkingLot>()), new Date(),new ArrayList<Visitor>(), new ArrayList<Employee>());
        appointments.add(appointment);
        Employee employee = new Employee(1111111L,"Axl", "Rose", "axl07@gmail.com", "+31093382637", false, appointments);

        //ACT section
        employee.setLastName("Tyler");
        String expectedLastName = "Tyler";

        //ASSERT section
        assertEquals(expectedLastName,employee.getLastName());
    }

    //setter and getter for 'email'
    @Test
    void emailSetterGetterTest(){

        //ARRANGE section
        List<Appointment> appointments = new ArrayList<>();
        Appointment appointment = new Appointment(1111111L, "Client Meeting", "Short feedback session with the client", new Location(11111L, "Location1", new Address(111L,"Street","222","21", "P5429DW", "Eindhoven", "Netherlands", "Netherlands"), new ArrayList<ParkingLot>()), new Date(),new ArrayList<Visitor>(), new ArrayList<Employee>());
        appointments.add(appointment);
        Employee employee = new Employee(1111111L,"Axl", "Rose", "axl07@gmail.com", "+31093382637", false, appointments);

        //ACT section
        employee.setEmail("rose@gmail.com");
        String expectedEmail = "rose@gmail.com";

        //ASSERT section
        assertEquals(expectedEmail,employee.getEmail());
    }

    //setter and getter for 'phone'
    @Test
    void phoneSetterGetterTest(){

        //ARRANGE section
        List<Appointment> appointments = new ArrayList<>();
        Appointment appointment = new Appointment(1111111L, "Client Meeting", "Short feedback session with the client", new Location(11111L, "Location1", new Address(111L,"Street","222","21", "P5429DW", "Eindhoven", "Netherlands", "Netherlands"), new ArrayList<ParkingLot>()), new Date(),new ArrayList<Visitor>(), new ArrayList<Employee>());
        appointments.add(appointment);
        Employee employee = new Employee(1111111L,"Axl", "Rose", "axl07@gmail.com", "+31093382637", false, appointments);

        //ACT section
        employee.setPhone("+31263355839");
        String expectedPhone = "+31263355839";

        //ASSERT section
        assertEquals(expectedPhone,employee.getPhone());
    }

    //setter and getter for 'is deleted'
    @Test
    void isDeletedSetterGetterTest(){

        //ARRANGE section
        List<Appointment> appointments = new ArrayList<>();
        Appointment appointment = new Appointment(1111111L, "Client Meeting", "Short feedback session with the client", new Location(11111L, "Location1", new Address(111L,"Street","222","21", "P5429DW", "Eindhoven", "Netherlands", "Netherlands"), new ArrayList<ParkingLot>()), new Date(),new ArrayList<Visitor>(), new ArrayList<Employee>());
        appointments.add(appointment);
        Employee employee = new Employee(1111111L,"Axl", "Rose", "axl07@gmail.com", "+31093382637", false, appointments);

        //ACT section
        employee.setDeleted(true);
        Boolean expectedIsDeleted = true;

        //ASSERT section
        assertTrue(employee.getDeleted());
    }

    //setter and getter for 'appointments'
    @Test
    void appointmentsSetterGetterTest(){

        //ARRANGE section
        List<Appointment> appointments = new ArrayList<>();
        Appointment appointment = new Appointment(1111111L, "Client Meeting", "Short feedback session with the client", new Location(11111L, "Location1", new Address(111L,"Street","222","21", "P5429DW", "Eindhoven", "Netherlands", "Netherlands"), new ArrayList<ParkingLot>()), new Date(),new ArrayList<Visitor>(), new ArrayList<Employee>());
        appointments.add(appointment);
        Employee employee = new Employee(1111111L,"Axl", "Rose", "axl07@gmail.com", "+31093382637", false, appointments);

        //ACT section
        employee.setAppointments(new ArrayList<Appointment>());

        //ASSERT section
        assertArrayEquals(new ArrayList<Appointment>().toArray(),employee.getAppointments().toArray());
    }
}
