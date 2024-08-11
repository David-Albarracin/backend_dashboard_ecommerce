
package pro.ddsr.backend_dashboard_ecommerce.customer.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pro.ddsr.backend_dashboard_ecommerce.customer.domain.dto.CustomerProjection;
import pro.ddsr.backend_dashboard_ecommerce.customer.persistence.Customer;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Define repository methods here
    // CU2, CU26 (CRUD)
    @Query("SELECT c FROM Customer c")
    List<CustomerProjection> findAlProjections();

    // CU8
    @Query("SELECT c FROM Customer c JOIN c.addresses ca JOIN ca.city ct WHERE ct.cityId = :cityId")
    List<Customer> findCustomersByCity(@Param("cityId") Long cityId);

    // CU15
    @Query("SELECT DISTINCT c FROM Customer c JOIN c.orders co WHERE co.status.id = :statusId")
    List<Customer> findCustomersWithPendingOrders(@Param("statusId") Long statusId);
}
