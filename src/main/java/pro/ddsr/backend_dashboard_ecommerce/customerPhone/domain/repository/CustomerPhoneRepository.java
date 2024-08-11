
package pro.ddsr.backend_dashboard_ecommerce.customerPhone.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.ddsr.backend_dashboard_ecommerce.customerPhone.persistence.CustomerPhone;

@Repository
public interface CustomerPhoneRepository extends JpaRepository<CustomerPhone, Long> {
    // Define repository methods here
}
