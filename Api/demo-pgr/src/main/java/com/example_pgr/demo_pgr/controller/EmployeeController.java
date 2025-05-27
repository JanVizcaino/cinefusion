package com.example_pgr.demo_pgr.controller;

import com.example_pgr.demo_pgr.dto.EmployeeDTO;
import com.example_pgr.demo_pgr.model.Employee;
import com.example_pgr.demo_pgr.model.Cine;
import com.example_pgr.demo_pgr.services.EmployeeService;
import com.example_pgr.demo_pgr.services.CineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CineService cineService;

    // OBTENER TODOS LOS EMPLEADOS
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<Employee> employees = employeeService.listEmployees();
        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<EmployeeDTO> employeeDTOS = employees.stream().map(employee -> {
            // Mapeamos Employee a EmployeeDTO
            EmployeeDTO dto = new EmployeeDTO();
            dto.setId_user(employee.getId_user());
            dto.setName(employee.getName());
            dto.setEmail(employee.getEmail());
            dto.setPassword(employee.getPassword());
            dto.setAddress(employee.getAddress());
            dto.setPhone(employee.getPhone());
            dto.setPosition(employee.getPosition());
            dto.setId_cine(employee.getCine() != null ? employee.getCine().getId_cine() : 0);
            dto.setDni(employee.getDni());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(employeeDTOS);
    }

    // OBTENER EMPLEADO POR LA ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId_user(employee.getId_user());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setPassword(employee.getPassword());
        dto.setAddress(employee.getAddress());
        dto.setPhone(employee.getPhone());
        dto.setPosition(employee.getPosition());
        dto.setId_cine(employee.getCine() != null ? employee.getCine().getId_cine() : 0);
        dto.setDni(employee.getDni());

        return ResponseEntity.ok(dto);
    }

    // CREAR UN NUEVO EMPLEADO
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        // Mapeamos el EmployeeDTO a Employee
        Cine cine = cineService.findById(employeeDTO.getId_cine());
        if (cine == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPassword(employeeDTO.getPassword());
        employee.setAddress(employeeDTO.getAddress());
        employee.setPhone(employeeDTO.getPhone());
        employee.setPosition(employeeDTO.getPosition());
        employee.setCine(cine);
        employee.setDni(employeeDTO.getDni());

        employeeService.saveEmployee(employee); // Guardamos el empleado

        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED); // Retornamos el EmployeeDTO con el estado 201 Created
    }

    // ACTUALIZAR UN EMPLEADO EXISTENTE
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable int id, @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        Cine cine = cineService.findById(employeeDTO.getId_cine());
        if (cine == null) {
            return ResponseEntity.badRequest().body(null);
        }

        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPassword(employeeDTO.getPassword());
        employee.setAddress(employeeDTO.getAddress());
        employee.setPhone(employeeDTO.getPhone());
        employee.setPosition(employeeDTO.getPosition());
        employee.setCine(cine);
        employee.setDni(employeeDTO.getDni());

        employeeService.saveEmployee(employee);

        return ResponseEntity.ok(employeeDTO);
    }

    // ELIMINAR UN EMPLEADO POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

