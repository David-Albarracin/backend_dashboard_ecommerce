
package pro.ddsr.backend_dashboard_ecommerce.supplierPhone.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.ddsr.backend_dashboard_ecommerce.supplierPhone.persistence.SupplierPhone;

@Repository
public interface SupplierPhoneRepository extends JpaRepository<SupplierPhone, Long> {
    // Define repository methods here
}
