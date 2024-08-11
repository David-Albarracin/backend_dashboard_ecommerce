
package pro.ddsr.backend_dashboard_ecommerce.orderDetail.domain.dto;

import pro.ddsr.backend_dashboard_ecommerce.product.persistence.Product;

public interface OrderDetailProjection {
     Long getOrderDetailId();
    Byte getAmount();
    Integer getUnitPrice();
    Integer getTotalPrice();
    Short getOrderLine();
    Product getProduct(); 
}
