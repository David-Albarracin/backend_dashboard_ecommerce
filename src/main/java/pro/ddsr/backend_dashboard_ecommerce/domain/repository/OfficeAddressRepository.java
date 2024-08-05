
package pro.ddsr.backend_dashboard_ecommerce.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.OfficeAddress;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeAddressRepository extends JpaRepository<OfficeAddress, Long> {
    // Define repository methods here
}
