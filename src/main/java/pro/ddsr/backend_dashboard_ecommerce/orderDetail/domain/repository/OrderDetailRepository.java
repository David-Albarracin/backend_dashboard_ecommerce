
package pro.ddsr.backend_dashboard_ecommerce.orderDetail.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pro.ddsr.backend_dashboard_ecommerce.orderDetail.domain.dto.OrderDetailProjection;
import pro.ddsr.backend_dashboard_ecommerce.orderDetail.persistence.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    
    @Query("SELECT d FROM OrderDetail d WHERE d.customerOrder.id = :orderId")
    List<OrderDetailProjection> findOrderDetailsByOrderId(@Param("orderId") Long orderId);
}
