package fontys.sem3.group.sioux.repository.appointment;

import fontys.sem3.group.sioux.dalInterfaces.IAppointmentDal;
import fontys.sem3.group.sioux.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public class AppointmentDalJPA implements IAppointmentDal {

    @Autowired
    IAppointmentRepository repo;

    @Override
    public Appointment getAppointmentByAppointmentId(Long appointmentId) {
        return repo.getAppointmentByAppointmentId(appointmentId);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return repo.findAll();
    }

    @Override
    public List<Appointment> getAllAppointmentsByDate(Date dateTime) {
        return repo.findAllByDateTime(dateTime);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        repo.save(appointment);
    }

    @Override
    public void deleteById(Long appointmentId) {
        repo.deleteById(appointmentId);
    }

    @Override
    public void save(Appointment appointment) {
        repo.save(appointment);
    }
}