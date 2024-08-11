
package pro.ddsr.backend_dashboard_ecommerce.transaction.persistence;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pro.ddsr.backend_dashboard_ecommerce.order.persistence.Order;
import pro.ddsr.backend_dashboard_ecommerce.payMethod.persistence.PayMethod;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "transaction_id", nullable = false)
    Long transactionId;

    @Column(precision = 10, scale = 2, nullable = false)
    @NotNull(message = "No puede ser nulo")
    private BigDecimal amount;
    
    @Column(name= "transaction_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "No puede ser nulo")
    private LocalDateTime transactionDate;

    @ManyToOne
    @JoinColumn(name = "pay_method_id")
    @NotNull(message = "No puede ser nulo")
    private PayMethod payMethod;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @NotNull(message = "No puede ser nulo")
    private Order order;
}