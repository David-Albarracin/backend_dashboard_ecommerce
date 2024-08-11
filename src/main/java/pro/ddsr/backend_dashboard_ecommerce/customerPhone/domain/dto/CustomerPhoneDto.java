
package pro.ddsr.backend_dashboard_ecommerce.customerPhone.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.ddsr.backend_dashboard_ecommerce.customer.persistence.Customer;
import pro.ddsr.backend_dashboard_ecommerce.customerPhone.persistence.CustomerPhone;
import pro.ddsr.backend_dashboard_ecommerce.customerPhone.persistence.CustomerPhone.TelephoneType;


@Data
@NoArgsConstructor
public class CustomerPhoneDto {

    Long customerPhoneId;

    @NotBlank(message = "Ingrese el numero de telefono")
    @NotNull(message = "No puede ser nulo")
    private String phoneNumber;

    @NotNull(message = "No puede ser nulo")
    private String telephoneType;

    private Long customerId;

    public static CustomerPhone toEntity( CustomerPhoneDto dto){
        // creacion de customer
        Customer newCustomer = new Customer();
        newCustomer.setCustomerId( dto.getCustomerId() );

        // creacion de la entidad
        CustomerPhone newPhone =  CustomerPhone.builder()
            .customer(newCustomer)
            .phoneNumber(dto.getPhoneNumber())
            .telephoneType( TelephoneType.valueOf( dto.getTelephoneType()))
            .build();
        
        // seteando id (si esta)
        if (dto.getCustomerPhoneId() != null){
            newPhone.setCustomerPhoneId( dto.getCustomerPhoneId());
        }

        // retorno
        return newPhone;

    }

}

