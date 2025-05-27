package com.example_pgr.demo_pgr.services;

    import com.example_pgr.demo_pgr.model.Employee;
import com.example_pgr.demo_pgr.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeReopsitory;

    public List<Employee> listEmployees() {
        return employeeReopsitory.findAll();
    }
    public Employee findById(Integer id) {
        return employeeReopsitory.findById(id).orElse(null);
    }
    public Employee saveEmployee(Employee employee) {
        return employeeReopsitory.save(employee);
    }
    public boolean deleteById(Integer id) {
        if (employeeReopsitory.existsById(id)) {
            employeeReopsitory.deleteById(id);
            return true;
        }
        return false;
    }
}
