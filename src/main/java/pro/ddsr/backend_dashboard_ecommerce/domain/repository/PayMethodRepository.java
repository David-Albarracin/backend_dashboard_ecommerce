
package pro.ddsr.backend_dashboard_ecommerce.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.PayMethod;
import org.springframework.stereotype.Repository;

@Repository
public interface PayMethodRepository extends JpaRepository<PayMethod, Long> {
    // Define repository methods here
}
