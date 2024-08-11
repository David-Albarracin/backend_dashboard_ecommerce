
package pro.ddsr.backend_dashboard_ecommerce.customer.persistence;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
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
import lombok.ToString;
import pro.ddsr.backend_dashboard_ecommerce.customerAddress.persistence.CustomerAddress;
import pro.ddsr.backend_dashboard_ecommerce.customerPhone.persistence.CustomerPhone;
import pro.ddsr.backend_dashboard_ecommerce.employee.persistence.Employee;
import pro.ddsr.backend_dashboard_ecommerce.order.persistence.Order;
import pro.ddsr.backend_dashboard_ecommerce.persistence.Audit;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "customer_id",length = 255)
    Long customerId;

    @Column(name = "first_name", length = 255, nullable = false)
    @NotBlank(message = "Ingrese el primer nombre")
    @NotNull(message = "No puede ser nulo")
    private String firstName;

    @Column(name = "first_surname", length = 255, nullable = false)
    @NotBlank(message = "Ingrese el primer apellido")
    @NotNull(message = "No puede ser nulo")
    private String firstSurname;

    @Column(name = "last_name", length = 255, nullable = false)
    @NotBlank(message = "Ingrese el segundo nombre")
    @NotNull(message = "No puede ser nulo")
    private String lastName;

    @Column(name = "last_surname", length = 255, nullable = false)
    @NotBlank(message = "Ingrese el segundo apellido")
    @NotNull(message = "No puede ser nulo")
    private String lastSurname;

    @Column(name = "document_number", length = 255, nullable = false)
    @NotBlank(message = "Ingrese el numero de documento")
    @NotNull(message = "No puede ser nulo")
    private String documentNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", length = 50, nullable = false)
    @NotNull(message = "No puede ser nulo")
    private DocumentType documentType;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    //@JsonBackReference
    private Set<CustomerAddress> addresses;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    //@JsonBackReference
    private Set<CustomerPhone> phones;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Order> orders;

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