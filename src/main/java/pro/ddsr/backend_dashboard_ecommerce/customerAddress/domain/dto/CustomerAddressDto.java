
package pro.ddsr.backend_dashboard_ecommerce.customerAddress.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.ddsr.backend_dashboard_ecommerce.city.persistence.City;
import pro.ddsr.backend_dashboard_ecommerce.customer.persistence.Customer;
import pro.ddsr.backend_dashboard_ecommerce.customerAddress.persistence.CustomerAddress;

@Data
@NoArgsConstructor
public class CustomerAddressDto {

    Long customerAddressId;

    @NotBlank(message = "Ingrese la direccion 1")
    @NotNull(message = "No puede ser nulo")
    String addressLine1;

    @NotBlank(message = "Ingrese la direccion 2")
    @NotNull(message = "No puede ser nulo")
    String addressLine2;

    Long customerId;

    @NotNull(message = "No puede ser nulo")
    Long cityId;

    public static CustomerAddress toEntity(CustomerAddressDto dto){

        // creacion del customer
        Customer newCustomer = new Customer();
        newCustomer.setCustomerId( dto.getCustomerId() );

        // creacion de la ciudad
        City newCity = new City();
        newCity.setCityId( dto.getCityId());

        // build
        CustomerAddress newAddress = CustomerAddress.builder()
            .addressLine1( dto.getAddressLine1())
            .addressLine2( dto.getAddressLine2())
            .city(newCity)
            .customer(newCustomer)
            .build();

        // seteando el id (si esta presente)
        if (dto.getCustomerAddressId() != null){
            newAddress.setCustomerAddressId( dto.getCustomerAddressId() );
        }

        return newAddress;
    }

}
