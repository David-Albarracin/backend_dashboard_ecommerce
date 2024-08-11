
package pro.ddsr.backend_dashboard_ecommerce.customerAddress.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
import pro.ddsr.backend_dashboard_ecommerce.city.persistence.City;
import pro.ddsr.backend_dashboard_ecommerce.customer.persistence.Customer;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="customer_address")
public class CustomerAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long customerAddressId;

    @Column(name = "address_line1", length = 50, nullable = false)
    @NotBlank(message = "Ingrese la direccion 1")
    @NotNull(message = "No puede ser nulo")
    private String addressLine1;

    @Column(name = "address_line2", length = 50, nullable = false)
    @NotBlank(message = "Ingrese la direccion 2")
    @NotNull(message = "No puede ser nulo")
    private String addressLine2;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @NotNull(message = "No puede ser nulo")
    @JsonBackReference
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @NotNull(message = "No puede ser nulo")
    //@JsonBackReference

    private City city;
}