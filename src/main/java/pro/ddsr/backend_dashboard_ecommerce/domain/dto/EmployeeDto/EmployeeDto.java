package pro.ddsr.backend_dashboard_ecommerce.domain.dto.EmployeeDto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Charge;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Employee;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Office;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Employee.DocumentType;

@Data
public class EmployeeDto {
        
    Long employeeId;

    
    @NotBlank(message = "Ingrese el primer nombre")
    @NotNull(message = "No puede ser nulo")
    private String firstName;

    
    @NotBlank(message = "Ingrese el segundo nombre")
    @NotNull(message = "No puede ser nulo")
    private String secondName;

    @NotBlank(message = "Ingrese el primer apellido")
    @NotNull(message = "No puede ser nulo")
    private String firstSurname;

    @NotBlank(message = "Ingrese el segundo apellido")
    @NotNull(message = "No puede ser nulo")
    private String secondSurname;
    
    @NotBlank(message = "Ingrese el número de documento")
    @NotNull(message = "No puede ser nulo")
    private String documentNumber;

    @NotNull(message = "No puede ser nulo")
    private String documentType;
    
    @NotBlank(message = "Ingrese el numero de telefono")
    @NotNull(message = "No puede ser nulo")
    private String phoneNumber;

  

    @NotNull(message = "No puede ser nulo")
    private Long officeId;

  
   

    @NotNull(message = "No puede ser nulo")
    private Integer extension;

  
    @NotNull(message = "No puede ser nulo")
    private Charge charge;

  
    private Long bossId;


    public static Employee toEntity( EmployeeDto dto, Office office, Employee boss){
        Employee employee = new Employee();
        if (dto.getEmployeeId() != null){
            employee.setEmployeeId( dto.getEmployeeId());
        }

        if (dto.getEmployeeId() != null){
            employee.setBoss( boss);
        }

        /*
         * TODO: Estructura de empleado, clientes y ordenes
        */
        // setteando el resto de los datos
            employee.setOffice(office);
            employee.setCharge( dto.getCharge());
            employee.setDocumentNumber( dto.getDocumentNumber());
            employee.setDocumentType( DocumentType.valueOf(dto.getDocumentType()));
            employee.setExtension( dto.getExtension());
            employee.setFirstName( dto.getFirstName());
            employee.setPhoneNumber( dto.getPhoneNumber());
            employee.setSecondName( dto.getSecondName());
            employee.setSecondSurname( dto.getSecondSurname());
            employee.setFirstSurname( dto.getFirstSurname());

        return employee;
      
    }


 


}
