package fontys.sem3.group.sioux.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

@Entity
@Table(name ="visitor")
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //unique identifier id
    private Long visitorId;

    //name
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;

    //licence plate
    @Column(name = "licencePlate")
    private String licencePlate;

    //contact details
    @Column(name = "phone")
    private String phone;

    //isDeleted
    @Column(name = "isDeleted")
    private Boolean isDeleted;

    //appointments
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "visitor_appointment", joinColumns = @JoinColumn(name = "visitorId"), inverseJoinColumns = @JoinColumn(name = "appointmentId"))
    private List<Appointment> appointments;

    //constructor
    public Visitor(Long visitorId, String firstName, String lastName, String licencePlate, String phone, Boolean isDeleted) {
        this.visitorId = visitorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.licencePlate = licencePlate;
        this.phone = phone;
        this.isDeleted = isDeleted;
        this.appointments = new ArrayList<>();
    }

    public Visitor(String firstName, String lastName, String licencePlate, String phone, Boolean isDeleted) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.licencePlate = licencePlate;
        this.phone = phone;
        this.isDeleted = isDeleted;
        this.appointments = new ArrayList<>();
    }

    public Visitor() {
    }

    public Visitor(Long visitorId, String firstName, String lastName, String licencePlate, String phone, Boolean isDeleted, List<Appointment> appointments) {
        this.visitorId = visitorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.licencePlate = licencePlate;
        this.phone = phone;
        this.isDeleted = isDeleted;
        this.appointments = appointments;
    }

    //getters and setters
    public Long getId() {
        return visitorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        appointment.getVisitors().add(this);
    }

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
        appointment.getVisitors().remove(this);
    }
}
