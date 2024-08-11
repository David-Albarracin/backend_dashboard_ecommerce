
package pro.ddsr.backend_dashboard_ecommerce.order.domain.dto;

import java.time.LocalDate;

import pro.ddsr.backend_dashboard_ecommerce.order.persistence.Order.OrderType;
import pro.ddsr.backend_dashboard_ecommerce.orderStatus.persistence.OrderStatus;

/**
 * Basicamente esta clase nos ayuda a resumir las ordenes para evitar la serializacion recursiva
*/
public interface OrderProjection {
    
    Long getOrderId();
    LocalDate getOrderDate();
    LocalDate getExpectedDate();
    LocalDate getDeliverDate();
    String getCommentary();
    OrderStatus getStatus();
    OrderType getOrderType();

}
