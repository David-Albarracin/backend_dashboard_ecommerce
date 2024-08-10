
package pro.ddsr.backend_dashboard_ecommerce.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import pro.ddsr.backend_dashboard_ecommerce.domain.dto.EmployeeDto.EmployeeDto;
import pro.ddsr.backend_dashboard_ecommerce.domain.dto.EmployeeDto.EmployeesWithOrdersDto;
import pro.ddsr.backend_dashboard_ecommerce.domain.repository.EmployeeRepository;
import pro.ddsr.backend_dashboard_ecommerce.domain.repository.OfficeRepository;
import pro.ddsr.backend_dashboard_ecommerce.domain.repository.OrderRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Employee;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Office;

@Service
public class EmployeeService {
    // Define service methods here
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    OfficeRepository officeRepository;

    @Autowired
    OrderRepository orderRepository;
    
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

    public List<EmployeesWithOrdersDto> findAllEmployeesWithOrders() {
        return orderRepository.findAllEmployeesWithOrders();
    }

    public Employee save(EmployeeDto dto) {

        Office office = this.officeRepository.findById(dto.getOfficeId()).get();
        Optional<Employee> optionalBoss = this.employeeRepository.findById(dto.getBossId());

        Employee boss = null;
       



        // construccion del obj de empleado a partir de dto
        Employee employee = EmployeeDto.toEntity(dto, office, boss);

        if (optionalBoss.isPresent()){
            employee.setBoss( optionalBoss.get());
            
        }

        
        
        return this.employeeRepository.save(employee);
    }

    public Optional<Employee> update(Long id, EmployeeDto dto) {

        Optional<Employee> optionalEmployee = this.employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {

            //SETS
            // nueva oficina 
            Office office = new Office();
            office.setOfficeId( dto.getOfficeId());

            // nuevo jefe
            Employee newEmployeeBoss = new Employee();
            newEmployeeBoss.setEmployeeId(dto.getBossId()); 

            // conversion a entidad
            Employee newEmployee = EmployeeDto.toEntity(dto, office, newEmployeeBoss);
            
            return Optional.of(this.employeeRepository.save(newEmployee));
        }
        return optionalEmployee;
    }
}
