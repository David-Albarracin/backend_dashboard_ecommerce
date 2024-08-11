
package pro.ddsr.backend_dashboard_ecommerce.officePhone.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.ddsr.backend_dashboard_ecommerce.officePhone.persistence.OfficePhone;

@Repository
public interface OfficePhoneRepository extends JpaRepository<OfficePhone, Long> {
    // Define repository methods here
}
