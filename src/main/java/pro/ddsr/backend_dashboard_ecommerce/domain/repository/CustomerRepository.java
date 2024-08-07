
package pro.ddsr.backend_dashboard_ecommerce.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Define repository methods here
    // CU2, CU26 (CRUD)

    // CU8
    @Query("SELECT c FROM Customer c JOIN c.addresses a INNER JOIN a.city ct WHERE ct.name = :cityName")
    List<Customer> findCustomersByCity(@Param("cityName") String cityName);
}
