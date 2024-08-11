
package pro.ddsr.backend_dashboard_ecommerce.supplier.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.ddsr.backend_dashboard_ecommerce.supplier.persistence.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    // Define repository methods here
}
