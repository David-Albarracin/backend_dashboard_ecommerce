
package pro.ddsr.backend_dashboard_ecommerce.payMethod.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="pay_method")
public class PayMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_method_id")
    Long payMethodId;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "Ingrese el nombre")
    @NotNull(message = "No puede ser nulo")
    private String name;

    @Column(length = 255)
    private String description;
}