
package pro.ddsr.backend_dashboard_ecommerce.payMethod.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.ddsr.backend_dashboard_ecommerce.payMethod.persistence.PayMethod;

@Repository
public interface PayMethodRepository extends JpaRepository<PayMethod, Long> {
    // Define repository methods here
}
