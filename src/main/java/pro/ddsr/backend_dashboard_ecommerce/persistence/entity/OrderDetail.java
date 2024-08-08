
package pro.ddsr.backend_dashboard_ecommerce.persistence.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

    @Column(nullable = false)
    @NotNull(message = "No puede ser nulo")
    private Byte amount;

    @Column(name = "unit_price", nullable = false)
    @NotNull(message = "No puede ser nulo")
    private Integer unitPrice;

    @Column(name = "total_price", nullable = false)
    @NotNull(message = "No puede ser nulo")
    private Integer totalPrice;

    @Column(name = "order_line")
    private Short orderLine;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(message = "No puede ser nulo")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @NotNull(message = "No puede ser nulo")
    @JsonBackReference
    private Order customerOrder;
}