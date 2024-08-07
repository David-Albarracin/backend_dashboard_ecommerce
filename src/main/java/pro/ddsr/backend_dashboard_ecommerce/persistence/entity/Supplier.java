
package pro.ddsr.backend_dashboard_ecommerce.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="supplier_id")
    Long supplierId;

      @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 50)
    private String contactName;

    @Column(length = 100)
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