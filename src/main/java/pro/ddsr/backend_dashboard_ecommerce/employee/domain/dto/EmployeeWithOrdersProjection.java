package pro.ddsr.backend_dashboard_ecommerce.employee.domain.dto;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeWithOrdersProjection {
    Long getEmployeeId();
    String getEmployeeName();
    List<OrderDto> getOrders();

    interface OrderDto {
        Long getOrderId();
        LocalDate getOrderDate();
        // Otros campos de la orden
    }
}
