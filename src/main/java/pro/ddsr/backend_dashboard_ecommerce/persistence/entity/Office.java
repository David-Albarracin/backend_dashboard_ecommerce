
package pro.ddsr.backend_dashboard_ecommerce.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name="office")
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long officeId;


    @Column(length = 50, nullable = false)
    private String addressLine1;

    @Column(length = 50, nullable = false)
    private String addressLine2;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

}
