
package pro.ddsr.backend_dashboard_ecommerce.customer.domain.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.ddsr.backend_dashboard_ecommerce.customer.persistence.Customer;
import pro.ddsr.backend_dashboard_ecommerce.customer.persistence.Customer.DocumentType;
import pro.ddsr.backend_dashboard_ecommerce.employee.persistence.Employee;

@Data
@NoArgsConstructor
public class CustomerDto {
    
    private Long customerId;

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
    private Set<pro.ddsr.backend_dashboard_ecommerce.customerAddress.domain.dto.CustomerAddressDto> addresses;

    @NotNull (message =  "el cliente debe tener telefonos")
    private Set<pro.ddsr.backend_dashboard_ecommerce.customerPhone.domain.dto.CustomerPhoneDto> phones;
    

    // para pasar de dto a entidad
    public static Customer toEntity( CustomerDto dto){

        // crear nuevo employee
        Employee newEmployee = new Employee();
        newEmployee.setEmployeeId( dto.getEmployeeId());

        // builder
        Customer newCustomer = Customer.builder()
            .employee(newEmployee)
            .firstName( dto.getFirstName() )
            .lastName( dto.getLastName() )
            .firstSurname( dto.getFirstSurname())
            .lastSurname( dto.getLastSurname())
            .documentNumber( dto.getDocumentNumber())
            .documentType( DocumentType.valueOf( dto.getDocumentType() ))
            .build();
        
        // seteo del id (si esta presente)
        if ( dto.getCustomerId() != null){
            newCustomer.setCustomerId( dto.getCustomerId());
        }

        return newCustomer;
    }
}

