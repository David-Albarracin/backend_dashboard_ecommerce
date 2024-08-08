
package pro.ddsr.backend_dashboard_ecommerce.persistence.crud;

import java.time.LocalDate;

import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Order.OrderType;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.OrderStatus;

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
