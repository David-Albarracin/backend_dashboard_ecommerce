
package pro.ddsr.backend_dashboard_ecommerce.charge.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.ddsr.backend_dashboard_ecommerce.charge.persistence.Charge;

@Repository
public interface ChargeRepository extends JpaRepository<Charge, Long> {
    // Define repository methods here
}
