package pro.ddsr.backend_dashboard_ecommerce.domain.dto.OrderDto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import pro.ddsr.backend_dashboard_ecommerce.persistence.crud.OrderDetailProjection;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Order;

/**
 * Esta clase es lo que se envia 
 * desde el back al front para mostrar 
 * en la interfaz
 */
@NoArgsConstructor
@Data
public class OrderDtoJavaOutput {

    private Long orderId;
    
    private LocalDate deliverDate;
    
    private LocalDate expectedDate;
    
    private LocalDate orderDate;

    private Long customerId;

    private String orderStatusName;

    private String commentary;

    private String orderType;
    
    private List<OrderDetailProjection> orderdetails;


    /**
     * 
     * @param summarizedDetails
     * @param order
     * @return el dto que se muestra al front
    */
    public static OrderDtoJavaOutput toDto(List<OrderDetailProjection> summarizedDetails, Order order){
        OrderDtoJavaOutput orderDto = new OrderDtoJavaOutput();

        orderDto.setCustomerId( order.getCustomer().getCustomerId());
        orderDto.setCommentary(order.getCommentary());

        if ( order.getDeliverDate() != null){
            orderDto.setDeliverDate(order.getDeliverDate());
        }
        orderDto.setExpectedDate( order.getExpectedDate());
        orderDto.setOrderDate( order.getOrderDate());
        orderDto.setOrderStatusName( order.getStatus().getName());
        orderDto.setOrderId( order.getOrderId());
        orderDto.setOrderType( order.getOrderType().name());
        orderDto.setOrderdetails(summarizedDetails);

        
        return orderDto;


    }
}
