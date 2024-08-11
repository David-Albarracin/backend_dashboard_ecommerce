
package pro.ddsr.backend_dashboard_ecommerce.city.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.ddsr.backend_dashboard_ecommerce.city.persistence.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    // Define repository methods here
}
