package pro.ddsr.backend_dashboard_ecommerce.domain.dto.OrderDto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Customer;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Order;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.OrderStatus;

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
    private List< OrderDetailDto> orderdetails;

    
    /**
     * Convierte el dto de la orden a la entidad de orden
    */
    public Order toOrder(Customer customer, OrderStatus orderStatus){

        Order order = new Order();

        if (this.orderId != null){
            order.setOrderId(this.orderId);
        }

        if ( this.deliverDate != null){
            order.setDeliverDate(this.deliverDate);
        }
        order.setOrderDate(this.orderDate);
        order.setExpectedDate(this.expectedDate);
        order.setStatus(orderStatus);
        order.setCommentary(this.commentary);
        order.setCustomer(customer);

        
        return order;
    }

    
}
