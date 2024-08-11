
package pro.ddsr.backend_dashboard_ecommerce.transaction.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pro.ddsr.backend_dashboard_ecommerce.order.persistence.Order;
import pro.ddsr.backend_dashboard_ecommerce.payMethod.persistence.PayMethod;
import pro.ddsr.backend_dashboard_ecommerce.transaction.persistence.Transaction;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    // Define attributes here
    private Long transactionId;

    private BigDecimal amount;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime transactionDate;

    private Long payMethod;

    private Long order;

    // Define constructor(s) here
    public Transaction toTransaction(PayMethod payMethod, Order order) {
        Transaction transaction = new Transaction();
        if (this.transactionId != null) {
            transaction.setTransactionId(this.transactionId);
        }
        transaction.setAmount(this.amount);
        transaction.setTransactionDate(this.transactionDate);
        transaction.setPayMethod(payMethod);
        transaction.setOrder(order);
        return transaction;
    }
}