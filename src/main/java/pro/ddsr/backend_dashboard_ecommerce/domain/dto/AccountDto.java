
package pro.ddsr.backend_dashboard_ecommerce.domain.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    @NotBlank (message = "username must not be empty")
    private String username;

    @NotBlank( message =  "password must not be empty")
    private String password;

    private Set<Role> roles;

}
