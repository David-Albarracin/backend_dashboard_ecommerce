
package pro.ddsr.backend_dashboard_ecommerce.productGama.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.ddsr.backend_dashboard_ecommerce.productGama.persistence.ProductGama;

@Repository
public interface ProductGamaRepository extends JpaRepository<ProductGama, Long> {
    // Define repository methods here
}
