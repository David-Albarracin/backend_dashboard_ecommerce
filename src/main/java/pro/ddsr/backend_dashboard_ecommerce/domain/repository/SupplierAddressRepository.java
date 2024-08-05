
package pro.ddsr.backend_dashboard_ecommerce.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.SupplierAddress;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierAddressRepository extends JpaRepository<SupplierAddress, Long> {
    // Define repository methods here
}
