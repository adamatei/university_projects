package fontys.sem3.group.sioux.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name ="appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appointmentId;

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 600)
    private String description;

    //location - unidirectional one to one
    //location has a name, address and parkinglots
    @OneToOne
    @JoinColumn(name = "locationId", referencedColumnName = "locationId")
    private Location location;


    @Column(name = "dateTime")
    private Date dateTime;

    //visitors linked to one appointment

    @ManyToMany(mappedBy = "appointments")
    private List<Visitor> visitors;

    //employees linked to one appointment

    @ManyToMany(mappedBy = "appointments")
    private List<Employee> employees;

    public Appointment() {
    }

    public Appointment(Long appointmentId, String name, String description, Location location, Date dateTime, List<Visitor> visitors, List<Employee> employees) {
        this.appointmentId = appointmentId;
        this.name = name;
        this.description = description;
        this.location = location;
        this.dateTime = dateTime;
        this.visitors = visitors;
        this.employees = employees;
    }

    public Long getId() {
        return appointmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addVisitor(Visitor visitor) {
        visitors.add(visitor);
        visitor.getAppointments().add(this);
    }

    public void removeVisitor(Visitor visitor) {
        visitors.remove(visitor);
        visitor.getAppointments().remove(this);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.getAppointments().add(this);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        employee.getAppointments().remove(this);
    }
}
