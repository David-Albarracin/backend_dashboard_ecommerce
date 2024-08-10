
package pro.ddsr.backend_dashboard_ecommerce.domain.dto.customerDto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.ddsr.backend_dashboard_ecommerce.domain.dto.CustomerAddressDto;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Customer;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.CustomerAddress;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.CustomerPhone;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Employee;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Customer.DocumentType;

@Data
@NoArgsConstructor
public class CustomerDto {
    
    private Long id;

    @NotBlank(message = "Ingrese el segundo nombre")
    @NotNull(message = "No puede ser nulo")
    private String firstName;

    @NotBlank(message = "Ingrese el segundo nombre")
    @NotNull(message = "No puede ser nulo")
    private String firstSurname;

    @NotBlank(message = "Ingrese el segundo nombre")
    @NotNull(message = "No puede ser nulo")
    private String lastName;

    @NotBlank(message = "Ingrese el segundo apellido")
    @NotNull(message = "No puede ser nulo")
    private String lastSurname;

    @NotBlank(message = "Ingrese el numero de documento")
    @NotNull(message = "No puede ser nulo")
    private String documentNumber;

    @NotNull(message = "No puede ser nulo")
    private String documentType;

    @NotNull( message =  "no puede ser nulo")
    private Long employeeId;

    @NotNull( message =  "el cliente debe tener direcciones")
    private Set<CustomerAddressDto> addresses;

    private Set<CustomerPhone> phones;
    

    // para pasar de dto a entidad
    public static Customer toEntity( CustomerDto dto, Employee employee, Set<CustomerAddress> address){
        Customer customer = new Customer();

        if ( dto.getId() != null){
            customer.setCustomerId( dto.getId());
        }

        // setters
        customer.setDocumentNumber( dto.getDocumentNumber());
        customer.setDocumentType(  DocumentType.valueOf( dto.getDocumentType()));
        customer.setFirstName( dto.getFirstName());
        customer.setFirstSurname( dto.getFirstSurname());
        customer.setLastName( dto.getLastName());
        customer.setLastSurname( dto.getLastSurname());
        customer.setEmployee(employee);
        customer.setAddresses(address);

        return customer;
    }
}

