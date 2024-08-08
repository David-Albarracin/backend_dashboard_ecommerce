
package pro.ddsr.backend_dashboard_ecommerce.persistence.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.enumObj.Audit;

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

    @Column(name = "first_name", length = 255, nullable = false)
    @NotBlank(message = "Ingrese el primer nombre")
    @NotNull(message = "No puede ser nulo")
    private String firstName;

    @Column(name = "second_name", length = 255, nullable = false)
    @NotBlank(message = "Ingrese el segundo nombre")
    @NotNull(message = "No puede ser nulo")
    private String secondName;

    @Column(name = "first_surname", length = 255, nullable = false)
    @NotBlank(message = "Ingrese el primer apellido")
    @NotNull(message = "No puede ser nulo")
    private String firstSurname;

    @Column(name = "second_surname", length = 255, nullable = false)
    @NotBlank(message = "Ingrese el segundo apellido")
    @NotNull(message = "No puede ser nulo")
    private String secondSurname;
    
    @Column(name = "document_number", length = 255, nullable = false)
    @NotBlank(message = "Ingrese el número de documento")
    @NotNull(message = "No puede ser nulo")
    private String documentNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", length = 50, nullable = false)
    @NotNull(message = "No puede ser nulo")
    private DocumentType documentType;
    
    @Column(name = "phone_number", length = 255, nullable = false)
    @NotBlank(message = "Ingrese el numero de telefono")
    @NotNull(message = "No puede ser nulo")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "office_id")
    @NotNull(message = "No puede ser nulo")
    // @JsonBackReference
    private Office office;

    @Column(nullable = false)
    @NotBlank(message = "Ingrese la extension")
    @NotNull(message = "No puede ser nulo")
    private Integer extension;

    @ManyToOne
    @JoinColumn(name = "charge_id")
    @NotNull(message = "No puede ser nulo")
    private Charge charge;

    @ManyToOne
    @JoinColumn(name = "boss_id")
    private Employee boss;

    @OneToMany(mappedBy = "employee")
    @JsonBackReference
    private Set<Customer> customers;

    @Embedded
    private final Audit audit = new Audit();
    
    @PrePersist
    public void prePersist() {
        audit.prePersistAudit();
    }

    @PreUpdate
    public void preUpdate() {
        audit.preUpdateAudit();
    }

    public enum DocumentType {
        CEDULA_CIUDADANIA,
        CEDULA_EXTRANJERIA,
        NIT,
        PASAPORTE
    }
}