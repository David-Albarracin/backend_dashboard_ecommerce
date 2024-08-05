
package pro.ddsr.backend_dashboard_ecommerce.persistence.entity;

import java.time.LocalDateTime;

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
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long customerId;

    @Column(length = 255, nullable = false)
    private String firstName;

    @Column(length = 255, nullable = false)
    private String firstSurname;

    @Column(length = 255, nullable = false)
    private String lastName;

    @Column(length = 255, nullable = false)
    private String lastSurname;

    @Column(length = 255, nullable = false)
    private String documentNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private DocumentType documentType;

    @Column(length = 255, nullable = false)
    private String employeeId;

    //Normalizar en una entidad
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    private Account account;

    public enum DocumentType {
        CEDULA_CIUDADANIA,
        CEDULA_EXTRANJERIA,
        NIT,
        PASAPORTE
    }

}
