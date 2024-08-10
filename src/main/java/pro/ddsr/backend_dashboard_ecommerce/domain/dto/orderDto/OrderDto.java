package pro.ddsr.backend_dashboard_ecommerce.domain.dto.orderDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Customer;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Order;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.OrderStatus;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Order.OrderType;

/**
 * Esta clase es el dto que se procesa cuando hay informacion de ordenes 
 * desde el frontEnd
*/
@Data
@NoArgsConstructor
public class OrderDto {
    // Define attributes here

    private Long orderId;
    

    private LocalDate deliverDate;

    @NotNull (message =  "Debe haber una fecha esperada")
    private LocalDate expectedDate;

    @NotNull (message =  "Debe haber una fecha en la que se puso la orden")
    private LocalDate orderDate;

    @NotNull(message =  "Hace falta el id del cliente")
    private Long customerId;

    // pendiente si es post
    private Long orderStatusId;


    private String commentary;

    @NotBlank (message =  "Debe especificarse el tipo de orden")
    private String orderType;

    @NotEmpty( message =  "No se puede hacer un pedido vacio")
    private Set< OrderDetailDto> orderdetails;

    
    /**
     * Convierte el dto de la orden a la entidad de orden
    */
    public static Order toOrder( OrderDto dto){

        // creacion de customer
        Customer newCustomer = new Customer();
        newCustomer.setCustomerId( dto.getCustomerId());

        // crecaion de un estado de orden
        OrderStatus newOrderStatus = new OrderStatus();
        newOrderStatus.setOrderStatusId( dto.getOrderStatusId() );

        // construyendo la entidad
        Order newOrder = Order.builder()
            .orderDate( dto.getOrderDate())
            .expectedDate( dto.getExpectedDate())
            .status( newOrderStatus)
            .customer(newCustomer)
            .orderType( OrderType.valueOf( dto.getOrderType()))
            .build();

        // setendo la fecha de entrega si esta presente
        if ( dto.getDeliverDate() != null){
            newOrder.setDeliverDate(dto.getDeliverDate());
        }

        // seteando el id, si esta presente
        if (dto.getOrderId()!= null){
            newOrder.setOrderId( dto.getOrderId() );
        }

        return newOrder;
    }

    
}
