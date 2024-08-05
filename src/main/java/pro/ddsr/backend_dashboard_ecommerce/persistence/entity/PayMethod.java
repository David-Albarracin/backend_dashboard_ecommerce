
package pro.ddsr.backend_dashboard_ecommerce.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="pay_method")
public class PayMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long payMethodId;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 255)
    private String description;

}
