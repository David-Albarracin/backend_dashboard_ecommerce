
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
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long employeeId;

    @Column(length = 255, unique = true, nullable = false)
    private String accountId;

    @Column(length = 255, nullable = false)
    private String firstName;

    @Column(length = 255, nullable = false)
    private String secondName;

    @Column(length = 255, nullable = false)
    private String firstSurname;

    @Column(length = 255, nullable = false)
    private String secondSurname;

    @Column(length = 255, nullable = false)
    private String documentNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private DocumentType documentType;

    @Column(length = 255, nullable = false)
    private String phoneNumber;

    @Column(length = 255, nullable = false)
    private String officeId;

    @Column(nullable = false)
    private Integer extension;

    @ManyToOne
    private Charge charge;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    private Employee boss;

    public enum DocumentType {
        CEDULA_CIUDADANIA,
        CEDULA_EXTRANJERIA,
        NIT,
        PASAPORTE
    }

}
