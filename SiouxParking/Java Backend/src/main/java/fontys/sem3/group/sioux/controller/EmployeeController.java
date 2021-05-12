package fontys.sem3.group.sioux.controller;
import fontys.sem3.group.sioux.model.Employee;
import fontys.sem3.group.sioux.serviceInterfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    //GET all the employees from the database
    @GetMapping()
    //GET at http://localhost:XXXX/employee
    public ResponseEntity<Iterable<Employee>> getAllEmployees(){
        var employees = employeeService.getAllEmployees();
        if (employees != null) {
            return ResponseEntity.ok().body(employees);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //ADD a employee
    @PostMapping()
    //POST at http://localhost:XXXX/employee/
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        //Create resource location
        var result = employeeService.getEmployeeByEmployeeId(employee.getEmployeeId()).getEmployeeId();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result)
                .toUri();

        //Send location in response (in the header)
        return ResponseEntity.created(location).build();
    }

    //GET employee by id
    @GetMapping("{id}")
    //GET at http://localhost:XXXX/employee/3
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long id) {
        var employee = employeeService.getEmployeeByEmployeeId(id);
        if(employee != null){
            return ResponseEntity.ok().body(employee);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    //UPDATE employee
    @PutMapping("{id}")
    //PUT at http://localhost:XXXX/employee/
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long id, @RequestBody Employee employee){
        Employee employeeData = employeeService.getEmployeeByEmployeeId(id);

        if (employeeData != null) {
            Employee updatedEmployee = employeeData;
            updatedEmployee.setFirstName(employee.getFirstName());
            updatedEmployee.setLastName(employee.getLastName());
            updatedEmployee.setEmail(employee.getEmail());
            updatedEmployee.setPhone(employee.getPhone());
            updatedEmployee.setDeleted(employee.getDeleted());
            employeeService.save(updatedEmployee);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //DELETE employee
    @DeleteMapping("{id}")
    //DELETE at http://localhost:XXXX/employee/3
    public ResponseEntity<HttpStatus> removeEmployee(@PathVariable(value = "id") Long id){
        try {
            employeeService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}