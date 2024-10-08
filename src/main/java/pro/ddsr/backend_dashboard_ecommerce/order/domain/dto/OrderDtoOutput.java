package pro.ddsr.backend_dashboard_ecommerce.order.domain.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import pro.ddsr.backend_dashboard_ecommerce.customer.persistence.Customer;
import pro.ddsr.backend_dashboard_ecommerce.order.persistence.Order;
import pro.ddsr.backend_dashboard_ecommerce.orderDetail.domain.dto.OrderDetailProjection;
import pro.ddsr.backend_dashboard_ecommerce.orderStatus.persistence.OrderStatus;

/**
 * Esta clase es lo que se envia 
 * desde el back al front para mostrar 
 * en la interfaz
 */
@NoArgsConstructor
@Data
public class OrderDtoOutput {

    private Long orderId;
    
    private LocalDate deliverDate;
    
    private LocalDate expectedDate;
    
    private LocalDate orderDate;

    private Customer customer;

    private OrderStatus status;

    private String commentary;

    private String orderType;
    
    private List<OrderDetailProjection> orderDetails;


    /**
     * 
     * @param summarizedDetails
     * @param order
     * @return el dto que se muestra al front
    */
    public static OrderDtoOutput toDto(List<OrderDetailProjection> summarizedDetails, Order order){
        OrderDtoOutput orderDto = new OrderDtoOutput();

        orderDto.setCustomer( order.getCustomer());
        orderDto.setCommentary(order.getCommentary());

        if ( order.getDeliverDate() != null){
            orderDto.setDeliverDate(order.getDeliverDate());
        }
        orderDto.setExpectedDate( order.getExpectedDate());
        orderDto.setOrderDate( order.getOrderDate());
        orderDto.setStatus( order.getStatus());
        orderDto.setOrderId( order.getOrderId());
        orderDto.setOrderType( order.getOrderType().name());
        orderDto.setOrderDetails(summarizedDetails);

        
        return orderDto;


    }
}
