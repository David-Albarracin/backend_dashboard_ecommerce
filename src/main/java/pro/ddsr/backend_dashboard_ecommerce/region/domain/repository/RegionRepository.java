
package pro.ddsr.backend_dashboard_ecommerce.region.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.ddsr.backend_dashboard_ecommerce.region.persistence.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    // Define repository methods here
}
