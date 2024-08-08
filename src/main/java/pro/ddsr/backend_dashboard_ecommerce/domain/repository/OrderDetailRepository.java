
package pro.ddsr.backend_dashboard_ecommerce.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pro.ddsr.backend_dashboard_ecommerce.persistence.crud.OrderDetailProjection;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.OrderDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    
    @Query("SELECT d FROM OrderDetail d WHERE d.customerOrder.id = :orderId")
    List<OrderDetailProjection> findOrderDetailsByOrderId(@Param("orderId") Long orderId);
}
