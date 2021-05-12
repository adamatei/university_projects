package fontys.sem3.group.sioux.service;
import fontys.sem3.group.sioux.dalInterfaces.IAppointmentDal;
import fontys.sem3.group.sioux.model.Appointment;
import fontys.sem3.group.sioux.serviceInterfaces.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentService implements IAppointmentService {

    IAppointmentDal dal;
    @Autowired
    public AppointmentService(IAppointmentDal dal)
    {
        this.dal =dal;
    }

    @Override
    public Appointment getAppointmentByAppointmentId(Long appointmentId) {
        return dal.getAppointmentByAppointmentId(appointmentId);
    }

    @Override
    public List<Appointment> getAllAppointments() { return dal.getAllAppointments(); }

    @Override
    public List<Appointment> getAllAppointmentsByDate(Date dateTime) {
        return dal.getAllAppointmentsByDate(dateTime);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        dal.addAppointment(appointment);
    }

    @Override
    public void deleteById(Long appointmentId) { dal.deleteById(appointmentId); }

    @Override
    public void save(Appointment appointment) {
        dal.save(appointment);
    }
}