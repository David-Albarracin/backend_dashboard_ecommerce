
package pro.ddsr.backend_dashboard_ecommerce.city.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pro.ddsr.backend_dashboard_ecommerce.region.persistence.Region;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long cityId;

    @Column(nullable = false)
    @NotBlank(message = "Ingrese el nombre")
    @NotNull(message = "No puede ser nulo")
    String name;

    @ManyToOne
    @JoinColumn(name = "region_id")
    @NotNull(message = "No puede ser nulo")


    Region region;

    // @OneToMany(mappedBy = "city")
    // private Set<CustomerAddress> addresses;
}