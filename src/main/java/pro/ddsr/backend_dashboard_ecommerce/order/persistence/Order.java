
package pro.ddsr.backend_dashboard_ecommerce.order.persistence;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
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
//import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pro.ddsr.backend_dashboard_ecommerce.customer.persistence.Customer;
import pro.ddsr.backend_dashboard_ecommerce.orderDetail.persistence.OrderDetail;
import pro.ddsr.backend_dashboard_ecommerce.orderStatus.persistence.OrderStatus;
import pro.ddsr.backend_dashboard_ecommerce.transaction.persistence.Transaction;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orders")
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    Long orderId;

    @Column(name = "order_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "No puede ser nulo")
    private LocalDate orderDate;

    @Column(name = "expected_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expectedDate;

    @Column(name = "deliver_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deliverDate;

    @Column(length = 45)
    private String commentary;

    @ManyToOne
    @JoinColumn(name = "order_status_id")
    @NotNull(message = "No puede ser nulo")
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_type", length = 20, nullable = false)
    @NotNull(message = "No puede ser nulo")
    private OrderType orderType;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @NotNull(message = "No puede ser nulo")
    @JsonBackReference
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Transaction> transactions;

    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<OrderDetail> orderDetails;


    public enum OrderType {
        COMPRA,
        VENTA
    }
}