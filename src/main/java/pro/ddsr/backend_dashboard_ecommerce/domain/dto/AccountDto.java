
package pro.ddsr.backend_dashboard_ecommerce.domain.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    @NotBlank (message = "email must not be empty")
    private String email;

    @NotBlank( message =  "password must not be empty")
    private String password;


    private Set<String> roles;

}
