
package pro.ddsr.backend_dashboard_ecommerce.customerAddress.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.ddsr.backend_dashboard_ecommerce.customerAddress.persistence.CustomerAddress;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {
    // Define repository methods here
}
