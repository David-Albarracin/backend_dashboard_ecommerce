
package pro.ddsr.backend_dashboard_ecommerce.domain.dto.orderDto;



import lombok.Data;
import lombok.NoArgsConstructor;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Order;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.OrderDetail;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Product;

@Data
@NoArgsConstructor
public class OrderDetailDto {

    private  Long orderDetailId;

    private Long productId;

    private Long orderId;

    private Byte amount;

    private Short lineNumber;

    private int totalPrice;

    private int unitPrice;


    public static OrderDetail toOrderDetail( OrderDetailDto dto){
        
        // creacion del producto
        Product product = new Product();
        product.setProductId( dto.getProductId() );

        // creacion de order
        Order newOrder = new Order();
        newOrder.setOrderId( dto.getOrderId());

       
        // creacion del objeto
        OrderDetail newOrderDetail = OrderDetail.builder()
            .product(product)
            .customerOrder(newOrder)
            .amount( dto.getAmount())
            .orderLine( dto.getLineNumber())
            .totalPrice( dto.getTotalPrice())
            .unitPrice( dto.getUnitPrice() )
            .build();

        // seteo del id ( si esta presente)
        if (dto.getOrderDetailId()  != null ){
            newOrderDetail.setOrderDetailId( dto.getOrderDetailId());
        }

        return newOrderDetail;
    }
}
