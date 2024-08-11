
package pro.ddsr.backend_dashboard_ecommerce.supplierAddress.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.ddsr.backend_dashboard_ecommerce.supplierAddress.persistence.SupplierAddress;

@Repository
public interface SupplierAddressRepository extends JpaRepository<SupplierAddress, Long> {
    // Define repository methods here
}
