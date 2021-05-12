package fontys.sem3.group.sioux.repository.appointment;
import fontys.sem3.group.sioux.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {
    Appointment getAppointmentByAppointmentId(Long appointmentId);
    List<Appointment> findAllByDateTime(Date dateTime);
    void deleteByAppointmentId(Long appointmentId);
}
