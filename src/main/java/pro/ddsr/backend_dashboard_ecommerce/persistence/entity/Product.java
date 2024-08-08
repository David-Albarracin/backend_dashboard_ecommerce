
package pro.ddsr.backend_dashboard_ecommerce.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long productId;

    @Column(length = 45, nullable = false, unique = true)
    @NotBlank(message = "Ingrese el codigo")
    @NotNull(message = "No puede ser nulo")
    private String code;

    @Column(length = 45, nullable = false)
    @NotBlank(message = "Ingrese el nombre")
    @NotNull(message = "No puede ser nulo")
    private String name;

    @Lob
    private String description;

    @Column(nullable = false)
    @NotNull(message = "No puede ser nulo")
    private Byte stock;

    @Column(name = "price_sale", nullable = false)
    @NotNull(message = "No puede ser nulo")
    private Integer priceSale;

    @Column(name = "price_buy")
    private Integer priceBuy;

    @ManyToOne
    @JoinColumn(name="product_gama_id")
    @NotNull(message = "No puede ser nulo")
    private ProductGama productGama;

    @ManyToOne
    @JoinColumn(name="supplier_id")
    @NotNull(message = "No puede ser nulo")
    private Supplier Supplier;
}