
package pro.ddsr.backend_dashboard_ecommerce.persistence.crud;

import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Product;

public interface OrderDetailProjection {
     Long getOrderDetailId();
    Byte getAmount();
    Integer getUnitPrice();
    Integer getTotalPrice();
    Short getOrderLine();
    Product getProduct(); 
}
