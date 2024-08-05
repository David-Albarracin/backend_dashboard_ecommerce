
package pro.ddsr.backend_dashboard_ecommerce.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="customer_phone")
public class CustomerPhone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long customerPhoneId;


    @Column(length = 50, nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private TelephoneType telephoneType;

    @ManyToOne
    private Customer customer;

    public enum TelephoneType {
        FIJO,
        CELULAR
    }

}