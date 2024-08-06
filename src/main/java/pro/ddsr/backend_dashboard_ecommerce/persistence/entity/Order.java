
package pro.ddsr.backend_dashboard_ecommerce.persistence.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long orderId;

    @Column
    private LocalDate orderDate;

    @Column
    private LocalDate expectedDate;

    @Column
    private LocalDate deliverDate;

    @Column(length = 45)
    private String commentary;

    @ManyToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private OrderType orderType;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "customerOrder")
    List<OrderDetail> orderdetails;

    public enum OrderType {
        COMPRA,
        VENTA
    }

}
