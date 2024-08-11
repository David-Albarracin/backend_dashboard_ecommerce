
package pro.ddsr.backend_dashboard_ecommerce.orderStatus.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.ddsr.backend_dashboard_ecommerce.orderStatus.persistence.OrderStatus;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
    // Define repository methods here
}
