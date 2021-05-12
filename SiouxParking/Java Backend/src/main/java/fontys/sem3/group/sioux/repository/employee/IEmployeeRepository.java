package fontys.sem3.group.sioux.repository.employee;
import fontys.sem3.group.sioux.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    Employee getEmployeeByEmployeeId(Long employeeId);
    void deleteByEmployeeId(Long employeeId);
}
