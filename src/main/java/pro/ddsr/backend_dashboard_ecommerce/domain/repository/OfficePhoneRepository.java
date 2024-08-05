
package pro.ddsr.backend_dashboard_ecommerce.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.OfficePhone;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficePhoneRepository extends JpaRepository<OfficePhone, Long> {
    // Define repository methods here
}
