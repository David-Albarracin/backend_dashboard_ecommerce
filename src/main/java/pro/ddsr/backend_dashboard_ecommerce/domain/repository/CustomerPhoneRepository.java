
package pro.ddsr.backend_dashboard_ecommerce.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.CustomerPhone;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerPhoneRepository extends JpaRepository<CustomerPhone, Long> {
    // Define repository methods here
}
