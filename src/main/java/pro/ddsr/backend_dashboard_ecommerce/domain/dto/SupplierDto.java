
package pro.ddsr.backend_dashboard_ecommerce.domain.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Supplier;

@Data
@NoArgsConstructor
public class SupplierDto {
    Long supplierId;
    private String name;
    private String contactName;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isActive;

    public Supplier toEntity() {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(this.supplierId);
        supplier.setName(this.name);
        supplier.setContactName(this.contactName);
        supplier.setEmail(this.email);

        return supplier;
    }

}
