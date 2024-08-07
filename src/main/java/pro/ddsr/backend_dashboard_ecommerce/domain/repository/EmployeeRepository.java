
package pro.ddsr.backend_dashboard_ecommerce.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Define repository methods here
    // CU5 (CRUD)

    // CU11
    @Query("SELECT e FROM Employee e INNER JOIN e.office eo WHERE eo.officeId =?1")
    List<Employee> findByOffice(Long idOffice);
}
