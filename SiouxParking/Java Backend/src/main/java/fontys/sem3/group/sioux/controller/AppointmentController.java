package fontys.sem3.group.sioux.controller;

import fontys.sem3.group.sioux.model.*;
import fontys.sem3.group.sioux.serviceInterfaces.IAddressService;
import fontys.sem3.group.sioux.serviceInterfaces.IAppointmentService;
import fontys.sem3.group.sioux.serviceInterfaces.IEmployeeService;
import fontys.sem3.group.sioux.serviceInterfaces.IVisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private IAppointmentService appointmentService;

    @Autowired
    private IVisitorService visitorService;

    @Autowired
    private IEmployeeService employeeService;

    //GET all the appointments from the database
    @GetMapping()
    //GET at http://localhost:XXXX/appointment
    public ResponseEntity<Iterable<Appointment>> getAllAppointments(){
        var appointments = appointmentService.getAllAppointments();
        if (appointments != null) {
            return ResponseEntity.ok().body(appointments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //GET appointment by id
    @GetMapping("{id}")
    //GET at http://localhost:XXXX/appointment/3
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable(value = "id") Long id) {
        var appointment = appointmentService.getAppointmentByAppointmentId(id);
        if(appointment != null){
            return ResponseEntity.ok().body(appointment);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    //GET appointment by date
    @GetMapping("{date}")
    //GET at http://localhost:XXXX/appointment/10-03-2021
    public ResponseEntity<Iterable<Appointment>> getAppointmentByDate(@PathVariable(value = "date") String dateTime) throws ParseException {

        String stringDate = "11-03-2021";
        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(stringDate);

        List<Appointment> appointments = appointmentService.getAllAppointmentsByDate(date);
        if(appointments != null){
            return ResponseEntity.ok().body(appointments);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    //ADD a appointment
    @PostMapping()
    //POST at http://localhost:XXXX/appointment/
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        appointmentService.addAppointment(appointment);
        //Create resource location
        var result = appointmentService.getAppointmentByAppointmentId(appointment.getId()).getId();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result)
                .toUri();

        //Send location in response (in the header)
        return ResponseEntity.created(location).build();
    }

    //UPDATE appointment
    @PutMapping("{id}")
    //PUT at http://localhost:XXXX/appointment/1
    public ResponseEntity<Appointment> updateAppointment(@PathVariable(value = "id") Long id, @RequestBody Appointment appointment){
        Appointment appointmentData = appointmentService.getAppointmentByAppointmentId(id);
        if (appointmentData != null) {
            Appointment updatedAppointment = appointmentData;
            updatedAppointment.setName(appointment.getName());
            updatedAppointment.setDescription(appointment.getDescription());
            updatedAppointment.setDateTime(appointment.getDateTime());
            appointmentService.save(updatedAppointment);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //UPDATE appointment to add visitor
    @PutMapping("/{appointmentId}/visitor/{visitorId}")
    //PUT at http://localhost:XXXX/appointment/3/visitor/1
    public ResponseEntity<Appointment> addVisitorToAppointment(@PathVariable Long appointmentId, @PathVariable Long visitorId){
        Appointment appointment = appointmentService.getAppointmentByAppointmentId(appointmentId);
        Visitor visitor = visitorService.getVisitorByVisitorId(visitorId);
        //TODO add check to see visitor has not already been added to the appointment
        appointment.addVisitor(visitor);
        appointmentService.save(appointment);
        if(appointment != null) {
            return ResponseEntity.ok().body(appointment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //UPDATE appointment to add employee
    @PutMapping("/{appointmentId}/employee/{employeeId}")
    //PUT at http://localhost:XXXX/appointment/3/employee/1
    public ResponseEntity<Appointment> addEmployeeToAppointment(@PathVariable Long appointmentId, @PathVariable Long employeeId){
        Appointment appointment = appointmentService.getAppointmentByAppointmentId(appointmentId);
        Employee employee = employeeService.getEmployeeByEmployeeId(employeeId);
        //TODO add check to see employee has not already been added to the appointment
        appointment.addEmployee(employee);
        appointmentService.save(appointment);
        if(appointment != null) {
            return ResponseEntity.ok().body(appointment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Delete visitor from appointment
    @DeleteMapping("/{appointmentId}/visitor/{visitorId}")
    //PUT at http://localhost:XXXX/appointment/3/visitor/1
    public ResponseEntity<Appointment> removeVisitorFromAppointment(@PathVariable Long appointmentId, @PathVariable Long visitorId){
        Appointment appointment = appointmentService.getAppointmentByAppointmentId(appointmentId);
        Visitor visitor = visitorService.getVisitorByVisitorId(visitorId);
        //TODO add check to see if visitor is part of the appointment first
        appointment.removeVisitor(visitor);
        appointmentService.save(appointment);
        if(appointment != null) {
            return ResponseEntity.ok().body(appointment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Delete employee from appointment
    @DeleteMapping("/{appointmentId}/employee/{employeeId}")
    //PUT at http://localhost:XXXX/appointment/3/employee/1
    public ResponseEntity<Appointment> removeEmployeeToAppointment(@PathVariable Long appointmentId, @PathVariable Long employeeId){
        Appointment appointment = appointmentService.getAppointmentByAppointmentId(appointmentId);
        Employee employee = employeeService.getEmployeeByEmployeeId(employeeId);
        //TODO add check to see employee has not already been added to the appointment
        appointment.removeEmployee(employee);
        appointmentService.save(appointment);
        if(appointment != null) {
            return ResponseEntity.ok().body(appointment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //DELETE appointment
    @DeleteMapping("{id}")
    //DELETE at http://localhost:XXXX/appointment/3
    public ResponseEntity<HttpStatus> removeAppointment(@PathVariable(value = "id") Long id){
        try {
            appointmentService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}