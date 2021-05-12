package fontys.sem3.group.sioux.serviceInterfaces;
import fontys.sem3.group.sioux.model.Employee;

import java.util.List;

public interface IEmployeeService {

    Employee getEmployeeByEmployeeId (Long employeeId);
    List<Employee> getAllEmployees();
    void addEmployee(Employee employee);
    void deleteById(Long employeeId);
    void save(Employee employee);

}