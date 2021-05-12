package fontys.sem3.group.sioux.repository.employee;

import fontys.sem3.group.sioux.dalInterfaces.IAddressDal;
import fontys.sem3.group.sioux.dalInterfaces.IEmployeeDal;
import fontys.sem3.group.sioux.model.Address;
import fontys.sem3.group.sioux.model.Employee;
import fontys.sem3.group.sioux.repository.address.IAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDalJPA implements IEmployeeDal {

    @Autowired
    IEmployeeRepository repo;

    @Override
    public Employee getEmployeeByEmployeeId(Long employeeId) {
        return repo.getEmployeeByEmployeeId(employeeId);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    @Override
    public void addEmployee(Employee employee) {
        repo.save(employee);
    }

    @Override
    public void deleteById(Long employeeId) {
        repo.deleteById(employeeId);
    }

    @Override
    public void save(Employee employee) {
        repo.save(employee);
    }
}