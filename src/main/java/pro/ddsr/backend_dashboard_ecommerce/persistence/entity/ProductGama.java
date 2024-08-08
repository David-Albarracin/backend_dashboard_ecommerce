
package pro.ddsr.backend_dashboard_ecommerce.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
@Table(name="product_gama")
public class ProductGama {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long productGamaId;

    @Column(nullable = false)
    @NotBlank(message = "Ingrese el nombre")
    @NotNull(message = "No puede ser nulo")
    String name;

    @Lob
    private String descriptionText;

    @Lob
    private String descriptionHtml;

    @Column(length = 450)
    private String image;
}