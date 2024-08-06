
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
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long employeeId;

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

    @ManyToOne
    @JoinColumn( name = "office_id")
    private Office office;

    @Column(nullable = false)
    private Integer extension;

    @ManyToOne
    @JoinColumn( name = "charge_id")
    private Charge charge;

    @ManyToOne
    @JoinColumn( name = "boss_id")
    private Employee boss;

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
