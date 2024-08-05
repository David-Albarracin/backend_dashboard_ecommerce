
package pro.ddsr.backend_dashboard_ecommerce.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Define repository methods here
    // CU2
}
