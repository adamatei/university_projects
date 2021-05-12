package fontys.sem3.group.sioux.service;

import fontys.sem3.group.sioux.dalInterfaces.IAppointmentDal;
import fontys.sem3.group.sioux.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class)
public class AppointmentServiceTest {

    @InjectMocks
    AppointmentService service;

    @Mock
    IAppointmentDal repository;

    @Test
    void getAllAppointmentsTest(){

        List<Appointment> appointments = new ArrayList<>();
        List<Visitor> visitors = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();

        Visitor visitor = new Visitor(1111111L,"Axl", "Rose", "J206NT", "+31093382637", false, null);
        Employee employee = new Employee(1111112L,"Steven", "Tyler", "tyler23@gmail.com", "+31447882497", false, null);

        visitors.add(visitor);
        employees.add(employee);

        Location location = new Location(11111L, "Location1", new Address(111L,"Street","222","21", "P5429DW", "Eindhoven", "Netherlands", "Netherlands"),null);
        Appointment appointment = new Appointment(1111111L, "Client Meeting", "Short feedback session with the client", location, new Date(),visitors, employees);

        appointments.add(appointment);

        when(repository.getAllAppointments()).thenReturn(appointments);
        List<Appointment> retrievedAppointments = service.getAllAppointments();

        assertArrayEquals(appointments.toArray(),retrievedAppointments.toArray());
    }


    @Test
    void getAppointmentByAppointmentIdTest(){

        Visitor visitor = new Visitor(1111111L,"Axl", "Rose", "J206NT", "+31093382637", false);
        Employee employee = new Employee(1111112L,"Steven", "Tyler", "tyler23@gmail.com", "+31447882497", false, null);

        Location location = new Location(11111L, "Location1", new Address(111L,"Street","222","21", "P5429DW", "Eindhoven", "Netherlands", "Netherlands"), null);
        Appointment appointment = new Appointment(1111111L, "Client Meeting", "Short feedback session with the client", location, new Date(),null, null);

        when(repository.getAppointmentByAppointmentId(1111111L)).thenReturn(appointment);
        Appointment retrievedAppointment = service.getAppointmentByAppointmentId(1111111L);

        assertEquals(appointment,retrievedAppointment);
    }


    @Test
    void getAllAppointmentsByDateTest(){

        List<Appointment> appointments = new ArrayList<>();
        List<Visitor> visitors = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();

        Visitor visitor = new Visitor(1111111L,"Axl", "Rose", "J206NT", "+31093382637", false);
        Employee employee = new Employee(1111112L,"Steven", "Tyler", "tyler23@gmail.com", "+31447882497", false, null);

        visitors.add(visitor);
        employees.add(employee);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String str="31-06-2021";
        java.util.Date date1 = null;
        try {
            date1 = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Location location = new Location(11111L, "Location1", new Address(111L,"Street","222","21", "P5429DW", "Eindhoven", "Netherlands", "Netherlands"), null);
        Appointment appointment = new Appointment(1111111L, "Client Meeting", "Short feedback session with the client", location, date1,visitors, employees);

        appointments.add(appointment);

        when(repository.getAllAppointmentsByDate(date1)).thenReturn(appointments);
        List<Appointment> retrievedAppointments = service.getAllAppointmentsByDate(date1);

        assertArrayEquals(appointments.toArray(),retrievedAppointments.toArray());
    }


    @Test
    void addAppointmentTest(){

        List<Visitor> visitors = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();

        Visitor visitor = new Visitor(1111111L,"Axl", "Rose", "J206NT", "+31093382637", false);
        Employee employee = new Employee(1111112L,"Steven", "Tyler", "tyler23@gmail.com", "+31447882497", false, null);

        visitors.add(visitor);
        employees.add(employee);

        Location location = new Location(11111L, "Location1", new Address(111L,"Street","222","21", "P5429DW", "Eindhoven", "Netherlands", "Netherlands"), null);
        Appointment appointment = new Appointment(1111111L, "Client Meeting", "Short feedback session with the client", location, new Date(),visitors, employees);

        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);

            assertEquals(appointment, arg0);
            return null;
        }).when(repository).save(any(Appointment.class));
        service.save(appointment);
    }


    @Test
    void deleteByIdTest(){

        List<Visitor> visitors = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();

        Visitor visitor = new Visitor(1111111L,"Axl", "Rose", "J206NT", "+31093382637", false);
        Employee employee = new Employee(1111112L,"Steven", "Tyler", "tyler23@gmail.com", "+31447882497", false, null);

        visitors.add(visitor);
        employees.add(employee);

        Location location = new Location(11111L, "Location1", new Address(111L,"Street","222","21", "P5429DW", "Eindhoven", "Netherlands", "Netherlands"), null);
        Appointment appointment = new Appointment(1111111L, "Client Meeting", "Short feedback session with the client", location, new Date(),visitors, employees);

        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);

            assertEquals(1111111L, arg0);
            return null;
        }).when(repository).deleteById(any(Long.class));
        service.deleteById(1111111L);
    }
}

