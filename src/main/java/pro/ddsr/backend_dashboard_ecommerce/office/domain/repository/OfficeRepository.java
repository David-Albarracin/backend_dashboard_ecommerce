
package pro.ddsr.backend_dashboard_ecommerce.office.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.ddsr.backend_dashboard_ecommerce.office.persistence.Office;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {
    // Define repository methods here
    // CU6 (CRUD)
}
