package fontys.sem3.group.sioux.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentTest {

    //constructor
    @Test
    void appointmentConstructorTest(){

        //ARRANGE section
        Location location = new Location(11111L, "Location1", new Address(111L,"Street","222","21", "P5429DW", "Eindhoven", "Netherlands", "Netherlands"),new ArrayList<ParkingLot>());
        Appointment appointment = new Appointment(1111111L, "Client Meeting", "Short feedback session with the client", location, new Date(),new ArrayList<Visitor>(), new ArrayList<Employee>());
        //Visitor visitor = new Visitor(1111111L,"Axl", "Rose", "J206NT", "+31093382637", false, new ArrayList<Appointment>());
        //Employee employee = new Employee(1111112L,"Steven", "Tyler", "tyler23@gmail.com", "+31447882497", false, new ArrayList<Appointment>());

        //ACT section
        long expectedId = 1111111L;
        String expectedName = "Client Meeting";
        String expectedDescription = "Short feedback session with the client";
        Location expectedLocation =  location;

        //ASSERT section
        assertEquals(expectedId,appointment.getId());
        assertEquals(expectedName,appointment.getName());
        assertEquals(expectedDescription,appointment.getDescription());
        assertEquals(expectedLocation,appointment.getLocation());
        assertArrayEquals(new ArrayList<Employee>().toArray(),appointment.getEmployees().toArray());
        assertArrayEquals(new ArrayList<Visitor>().toArray(),appointment.getVisitors().toArray());
    }

    //setter and getter for 'name'
    @Test
    void nameSetterGetterTest(){

        //ARRANGE section
        Location location = new Location(11111L, "Location1", new Address(111L,"Street","222","21", "P5429DW", "Eindhoven", "Netherlands", "Netherlands"),new ArrayList<ParkingLot>());
        Appointment appointment = new Appointment(1111111L, "Client Meeting", "Short feedback session with the client", location, new Date(),new ArrayList<Visitor>(), new ArrayList<Employee>());
        //Visitor visitor = new Visitor(1111111L,"Axl", "Rose", "J206NT", "+31093382637", false, new ArrayList<Appointment>());
        //Employee employee = new Employee(1111112L,"Steven", "Tyler", "tyler23@gmail.com", "+31447882497", false, new ArrayList<Appointment>());

        //ACT section
        appointment.setName("Stand up");
        String expectedName = "Stand up";

        //ASSERT section
        assertEquals(expectedName,appointment.getName());
    }

    //setter and getter for 'description'
    @Test
    void descriptionSetterGetterTest(){

        //ARRANGE section
        Location location = new Location(11111L, "Location1", new Address(111L,"Street","222","21", "P5429DW", "Eindhoven", "Netherlands", "Netherlands"),new ArrayList<ParkingLot>());
        Appointment appointment = new Appointment(1111111L, "Client Meeting", "Short feedback session with the client", location, new Date(),new ArrayList<Visitor>(), new ArrayList<Employee>());
        //Visitor visitor = new Visitor(1111111L,"Axl", "Rose", "J206NT", "+31093382637", false, new ArrayList<Appointment>());
        //Employee employee = new Employee(1111112L,"Steven", "Tyler", "tyler23@gmail.com", "+31447882497", false, new ArrayList<Appointment>());

        //ACT section
        appointment.setDescription("Sprint Review");
        String expectedDescription = "Sprint Review";

        //ASSERT section
        assertEquals(expectedDescription,appointment.getDescription());
    }

    //setter and getter for 'location'
    @Test
    void locationSetterGetterTest(){

        //ARRANGE section
        Location location = new Location(11111L, "Location1", new Address(111L,"Street","222","21", "P5429DW", "Eindhoven", "Netherlands", "Netherlands"),new ArrayList<ParkingLot>());
        Appointment appointment = new Appointment(1111111L, "Client Meeting", "Short feedback session with the client", location, new Date(),new ArrayList<Visitor>(), new ArrayList<Employee>());
        //Visitor visitor = new Visitor(1111111L,"Axl", "Rose", "J206NT", "+31093382637", false, new ArrayList<Appointment>());
        //Employee employee = new Employee(1111112L,"Steven", "Tyler", "tyler23@gmail.com", "+31447882497", false, new ArrayList<Appointment>());

        //ACT section
        Location newLocation = new Location(11112L, "Location2", new Address(112L,"Street2","221","45", "P5799DW", "Eindhoven", "Netherlands", "Netherlands"),new ArrayList<ParkingLot>());
        appointment.setLocation(newLocation);
        Location expectedLocation = newLocation;

        //ASSERT section
        assertEquals(expectedLocation,appointment.getLocation());
    }

    //setter and getter for 'date'
    @Test
    void dateSetterGetterTest(){

        //ARRANGE section
        Location location = new Location(11111L, "Location1", new Address(111L,"Street","222","21", "P5429DW", "Eindhoven", "Netherlands", "Netherlands"),new ArrayList<ParkingLot>());
        Appointment appointment = new Appointment(1111111L, "Client Meeting", "Short feedback session with the client", location, new Date(),new ArrayList<Visitor>(), new ArrayList<Employee>());
        //Visitor visitor = new Visitor(1111111L,"Axl", "Rose", "J206NT", "+31093382637", false, new ArrayList<Appointment>());
        //Employee employee = new Employee(1111112L,"Steven", "Tyler", "tyler23@gmail.com", "+31447882497", false, new ArrayList<Appointment>());

        //ACT section
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String str="31-06-2021";
        java.util.Date date1 = null;
        try {
            date1 = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        appointment.setDateTime(date1);
        Date expectedDate = date1;

        //ASSERT section
        assertEquals(expectedDate,appointment.getDateTime());
    }

    //setter and getter for 'visitors'
    @Test
    void visitorsSetterGetterTest(){

        //ARRANGE section
        Location location = new Location(11111L, "Location1", new Address(111L,"Street","222","21", "P5429DW", "Eindhoven", "Netherlands", "Netherlands"),new ArrayList<ParkingLot>());
        Appointment appointment = new Appointment(1111111L, "Client Meeting", "Short feedback session with the client", location, new Date(),new ArrayList<Visitor>(), new ArrayList<Employee>());
        //Employee employee = new Employee(1111112L,"Steven", "Tyler", "tyler23@gmail.com", "+31447882497", false, new ArrayList<Appointment>());

        //ACT section
        List<Visitor> newVisitors = new ArrayList<>();
        Visitor visitor = new Visitor(1111111L,"Axl", "Rose", "J206NT", "+31093382637", false, new ArrayList<Appointment>());
        newVisitors.add(visitor);
        appointment.setVisitors(newVisitors);
        List<Visitor> expectedVisitors = newVisitors;

        //ASSERT section
        assertEquals(expectedVisitors,appointment.getVisitors());
    }

    //setter and getter for 'employees'
    @Test
    void employeesSetterGetterTest(){

        //ARRANGE section
        Location location = new Location(11111L, "Location1", new Address(111L,"Street","222","21", "P5429DW", "Eindhoven", "Netherlands", "Netherlands"),new ArrayList<ParkingLot>());
        Appointment appointment = new Appointment(1111111L, "Client Meeting", "Short feedback session with the client", location, new Date(),new ArrayList<Visitor>(), new ArrayList<Employee>());

        //ACT section
        List<Employee> newEmployees = new ArrayList<>();
        Employee employee = new Employee(1111112L,"Steven", "Tyler", "tyler23@gmail.com", "+31447882497", false, new ArrayList<Appointment>());
        newEmployees.add(employee);
        appointment.setEmployees(newEmployees);
        List<Employee> expectedEmployees = newEmployees;

        //ASSERT section
        assertEquals(expectedEmployees,appointment.getEmployees());
    }
}
