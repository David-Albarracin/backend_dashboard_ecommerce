
package pro.ddsr.backend_dashboard_ecommerce.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.SupplierPhone;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierPhoneRepository extends JpaRepository<SupplierPhone, Long> {
    // Define repository methods here
}
