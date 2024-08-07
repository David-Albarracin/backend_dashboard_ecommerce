
package pro.ddsr.backend_dashboard_ecommerce.persistence.entity;



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
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
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
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "customer_id",length = 255, nullable = false)
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

    @ManyToOne
    @JoinColumn(name = "employee_id")
    Employee employee;

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
