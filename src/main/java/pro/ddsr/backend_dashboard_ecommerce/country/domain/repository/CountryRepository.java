
package pro.ddsr.backend_dashboard_ecommerce.country.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.ddsr.backend_dashboard_ecommerce.country.persistence.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    // Define repository methods here
}
