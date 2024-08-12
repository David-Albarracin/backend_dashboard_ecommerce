
package pro.ddsr.backend_dashboard_ecommerce.customerPhone.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import pro.ddsr.backend_dashboard_ecommerce.customer.persistence.Customer;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="customer_phone")
public class CustomerPhone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long customerPhoneId;

    @Column(name = "phone_number", length = 50, nullable = false)
    @NotBlank(message = "Ingrese el numero de telefono")
    @NotNull(message = "No puede ser nulo")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "telephone_type", length = 20, nullable = false)
    @NotNull(message = "No puede ser nulo")
    private TelephoneType telephoneType;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @NotNull(message = "No puede ser nulo")
    @JsonBackReference
    private Customer customer;

    public enum TelephoneType {
        FIJO,
        CELULAR
    }
}