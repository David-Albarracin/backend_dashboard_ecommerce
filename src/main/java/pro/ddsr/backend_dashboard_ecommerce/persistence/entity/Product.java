
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
    private String code;

    @Column(length = 45)
    private String name;

    @Lob
    private String description;

    @Column
    private Byte stock;

    @Column
    private Integer priceSale;

    @Column
    private Integer priceBuy;

    @ManyToOne
    @JoinColumn(name="product_gama_id")
    private ProductGama productGama;

}