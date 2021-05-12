package fontys.sem3.group.sioux.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //unique identifier id
    private Long employeeId;

    //name
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;

    //email
    @Column(name = "email")
    private String email;

    //contact details
    @Column(name = "phone")
    private String phone;

    //isDeleted
    @Column(name = "isDeleted")
    private Boolean isDeleted;

    //appointments
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "employee_appointment", joinColumns = @JoinColumn(name = "employeeId"), inverseJoinColumns = @JoinColumn(name = "appointmentId"))
    private List<Appointment> appointments;

    public Employee() {
    }

    public Employee(Long employeeId, String firstName, String lastName, String email, String phone, Boolean isDeleted, List<Appointment> appointments) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.isDeleted = isDeleted;
        this.appointments = appointments;
    }

    public Long getEmployeeId() {
        return employeeId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        appointment.getEmployees().add(this);
    }

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
        appointment.getEmployees().remove(this);
    }
}
