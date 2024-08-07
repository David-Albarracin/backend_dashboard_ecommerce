
package pro.ddsr.backend_dashboard_ecommerce.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backend_dashboard_ecommerce.domain.repository.EmployeeRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Employee;

@Service
public class EmployeeService {
    // Define service methods here
    @Autowired
    EmployeeRepository employeeRepository;
    
    @Transactional
    public Optional<Employee> delete(Long id) {
        Optional<Employee> optionalEmployee = this.employeeRepository.findById(id);
        optionalEmployee.ifPresent(
            EmployeeFound -> {
                this.employeeRepository.delete(EmployeeFound);
            }
        );
        return optionalEmployee;
    }
 
    public List<Employee> findAll() {
        return (List<Employee>) this.employeeRepository.findAll();
    }

    public Optional<Employee> findById(Long id) {
        return this.employeeRepository.findById(id);
    }

    public List<Employee> findByOffice(Long id) {
        return this.employeeRepository.findByOffice(id);
    }

    public List<Employee> findAllEmployeesWithOrders() {
        return employeeRepository.findAllEmployeesWithOrders();
    }

    public Employee save(Employee Employee) {
        return this.employeeRepository.save(Employee);
    }

    public Optional<Employee> update(Long id, Employee employee) {
        Optional<Employee> optionalEmployee = this.employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employeeItem = optionalEmployee.orElseThrow();
            //SETS
            
            return Optional.of(this.employeeRepository.save(employeeItem));
        }
        return optionalEmployee;
    }
}
