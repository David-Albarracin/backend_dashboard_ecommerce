
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


    public OrderDetail toOrderDetail(Product product, Order order){
        
        OrderDetail orderDetail = new OrderDetail();

        if (this.orderDetailId  != null ){
            orderDetail.setOrderDetailId(this.orderDetailId);
        }

        orderDetail.setProduct(product);
        orderDetail.setCustomerOrder(order);
        orderDetail.setOrderLine(this.lineNumber);
        orderDetail.setTotalPrice(this.totalPrice);
        orderDetail.setAmount(this.amount);


        return orderDetail;
    }
}
