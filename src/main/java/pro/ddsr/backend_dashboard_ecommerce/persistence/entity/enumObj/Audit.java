package pro.ddsr.backend_dashboard_ecommerce.persistence.entity.enumObj;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Embeddable
public class Audit{

    @Column( name = "created_at")
    private LocalDateTime createdAt;

    @Column (name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    Boolean isActive;


    // se llena el campo de created_at cuando el objeto se crea
    @PrePersist
    public void prePersistAudit(){
        this.createdAt = LocalDateTime.now();
        this.isActive = true;

    }


    // se llena el campo de updated_at cuando el objeto se modifica
    @PreUpdate
    public void preUpdateAudit(){
        this.updatedAt = LocalDateTime.now();
    }
}