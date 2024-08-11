
package pro.ddsr.backend_dashboard_ecommerce.supplier.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pro.ddsr.backend_dashboard_ecommerce.persistence.Audit;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="supplier_id")
    Long supplierId;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Ingrese el nombre")
    @NotNull(message = "No puede ser nulo")
    private String name;

    @Column(name = "contact_name", length = 50, nullable = false)
    @NotBlank(message = "Ingrese el nombre del contacto")
    @NotNull(message = "No puede ser nulo")
    private String contactName;

    @Column(length = 100, nullable = false)
    @NotNull(message = "No puede ser nulo")
    @Email
    private String email;

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
}