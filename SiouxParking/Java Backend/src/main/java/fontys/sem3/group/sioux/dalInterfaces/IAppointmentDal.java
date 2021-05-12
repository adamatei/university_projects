package fontys.sem3.group.sioux.dalInterfaces;

import fontys.sem3.group.sioux.model.Address;
import fontys.sem3.group.sioux.model.Appointment;

import java.util.Date;
import java.util.List;

public interface IAppointmentDal {
    Appointment getAppointmentByAppointmentId (Long appointmentId);
    List<Appointment> getAllAppointments();
    List<Appointment> getAllAppointmentsByDate(Date dateTime);
    void addAppointment(Appointment appointment);
    void deleteById(Long appointmentId);
    void save(Appointment appointment);
}