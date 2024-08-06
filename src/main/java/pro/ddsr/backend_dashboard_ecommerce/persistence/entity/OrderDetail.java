
package pro.ddsr.backend_dashboard_ecommerce.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="order_detail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long orderDetailId;

     @Column
    private Byte amount;

    @Column
    private Integer unitPrice;

    @Column
    private Integer totalPrice;

    @Column
    private Short orderLine;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order customerOrder;

}
