package fontys.sem3.group.sioux.service;
import fontys.sem3.group.sioux.dalInterfaces.IEmployeeDal;
import fontys.sem3.group.sioux.model.Employee;
import fontys.sem3.group.sioux.serviceInterfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    IEmployeeDal dal;
    @Autowired
    public EmployeeService(IEmployeeDal dal)
    {
        this.dal =dal;
    }

    @Override
    public Employee getEmployeeByEmployeeId(Long employeeId) {
        return dal.getEmployeeByEmployeeId(employeeId);
    }

    @Override
    public List<Employee> getAllEmployees() { return dal.getAllEmployees(); }

    @Override
    public void addEmployee(Employee employee) {
        dal.addEmployee(employee);
    }

    @Override
    public void deleteById(Long employeeId) { dal.deleteById(employeeId); }

    @Override
    public void save(Employee employee) {
        dal.save(employee);
    }
}