package pro.ddsr.backend_dashboard_ecommerce.office.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pro.ddsr.backend_dashboard_ecommerce.city.persistence.City;
import pro.ddsr.backend_dashboard_ecommerce.office.persistence.Office;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfficeDto {
    // Define attributes here
    private Long officeId;

    private String addressLine1;

    private String addressLine2;

    private Long city;

    // Define constructor(s) here
    public Office toOffice(City city) {
        Office office = new Office();
        if (this.officeId != null) {
            office.setOfficeId(this.officeId);
        }
        office.setAddressLine1(this.addressLine1);
        office.setAddressLine2(this.addressLine2);
        office.setCity(city);
        return office;
    }

    // Define getter and setter methods here
}