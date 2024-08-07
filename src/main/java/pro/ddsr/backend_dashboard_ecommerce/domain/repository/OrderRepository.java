
package pro.ddsr.backend_dashboard_ecommerce.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Define repository methods here
    // CU3, CU27 (CRUD)

    // CU9
    @Query("SELECT o FROM Order o INNER JOIN o.status os WHERE os.name =?1")
    List<Order> findByStatus(String statusName);
}
