
package pro.ddsr.backend_dashboard_ecommerce.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.ProductGama;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductGamaRepository extends JpaRepository<ProductGama, Long> {
    // Define repository methods here
}
