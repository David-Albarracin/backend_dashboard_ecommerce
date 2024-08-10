
package pro.ddsr.backend_dashboard_ecommerce.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.ddsr.backend_dashboard_ecommerce.domain.dto.EmployeeDto.EmployeeDto;
import pro.ddsr.backend_dashboard_ecommerce.domain.dto.EmployeeDto.EmployeesWithOrdersDto;
import pro.ddsr.backend_dashboard_ecommerce.domain.service.EmployeeService;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Employee;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public List<Employee> listEmployee(){
        return this.employeeService.findAll();
    }

    @GetMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Employee> view(@PathVariable Long id){
        Optional<Employee> optionalEmployee  = employeeService.findById(id);
        if (optionalEmployee.isPresent()){
            return ResponseEntity.ok(optionalEmployee.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // CU11
    @GetMapping("/byoffice/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Employee>> viewByOffice(@PathVariable Long id){
        List<Employee> listEmployee  = employeeService.findByOffice(id);
        if (listEmployee.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listEmployee);
    }

    // CU16
    @GetMapping("/withorders")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<EmployeesWithOrdersDto>> viewEmployeesWithOrders(){
        List<EmployeesWithOrdersDto> listEmployee  = employeeService.findAllEmployeesWithOrders();
        if (listEmployee.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listEmployee);
    }

    @PostMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody EmployeeDto employee, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.save(employee));
    }

    @PutMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Employee> update(@PathVariable Long id, @Valid @RequestBody EmployeeDto employee){
        Optional<Employee> employeeOptional = this.employeeService.update(id, employee);
        if (employeeOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id){
        
        try {
            Optional<Employee> optionalEmployee = this.employeeService.delete(id);
            if (optionalEmployee.isPresent()){
                return ResponseEntity.ok(optionalEmployee.orElseThrow());
            }
            return ResponseEntity.notFound().build();

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No se puede eliminar el empleado ya que esta asignado a un cliente");
        }
        
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
