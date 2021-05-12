package fontys.sem3.group.sioux.service;

import fontys.sem3.group.sioux.dalInterfaces.IEmployeeDal;
import fontys.sem3.group.sioux.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    EmployeeService service;

    @Mock
    IEmployeeDal repository;

    @Test
    void getAllEmployeesTest() {

        List<Employee> employees = new ArrayList<>();

        Employee employee1 = new Employee(1111111L,"Axl", "Rose", "axl07@gmail.com", "+31093382637", false,null);
        Employee employee2 = new Employee(2111111L,"Axl2", "Rose2", "axl072@gmail.com", "+31446982637", false, null);

        employees.add(employee1);
        employees.add(employee2);

        when(repository.getAllEmployees()).thenReturn(employees);
        List<Employee> retrievedEmployees = service.getAllEmployees();

        assertArrayEquals(employees.toArray(),retrievedEmployees.toArray());
    }

    @Test
    void getEmployeeByEmployeeIdTest() {

        Employee employee = new Employee(1111111L,"Axl", "Rose", "axl07@gmail.com", "+31093382637", false, null);

        when(repository.getEmployeeByEmployeeId(1111111L)).thenReturn(employee);
        Employee retrievedEmployee = service.getEmployeeByEmployeeId(1111111L);

        assertEquals(employee,retrievedEmployee);
    }

    @Test
    void addEmployeeTest() {

        Employee employee = new Employee(1111111L,"Axl", "Rose", "axl07@gmail.com", "+31093382637", false, null);
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);

            assertEquals(employee, arg0);
            return null;
        }).when(repository).addEmployee(any(Employee.class));
        service.addEmployee(employee);
    }

    @Test
    void deleteByIdTest() {

        Employee employee = new Employee(1111111L,"Axl", "Rose", "axl07@gmail.com", "+31093382637", false, null);
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);

            assertEquals(1111111L, arg0);
            return null;
        }).when(repository).deleteById(any(Long.class));
        service.deleteById(1111111L);
    }

    @Test
    void saveTest() {

        Employee employee = new Employee(1111111L,"Axl", "Rose", "axl07@gmail.com", "+31093382637", false, null);
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);

            assertEquals(employee, arg0);
            return null;
        }).when(repository).save(any(Employee.class));
        service.save(employee);
    }
}

