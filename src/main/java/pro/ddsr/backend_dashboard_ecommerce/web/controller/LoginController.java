package pro.ddsr.backend_dashboard_ecommerce.web.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import pro.ddsr.backend_dashboard_ecommerce.domain.dto.AccountDto;
import pro.ddsr.backend_dashboard_ecommerce.domain.repository.AccountRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Account;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Role;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.enumObj.RoleEnum;

@RestController
public class LoginController {
     @Autowired 
    private AccountRepository accountRepository;

    @Autowired  // inyecta el bean de la configuracion de seguridad  
    private PasswordEncoder passwordEncoder;

     @GetMapping("/access-admin")
     @PreAuthorize("hasRole('ADMIN')")
     public String getTheAdmin() {
         return "GET:: ADMIN";
     }

     @PostMapping("/create-user")
    // // @PreAuthorize("permitAll()")
     public ResponseEntity<?> createUser(@Valid @RequestBody AccountDto accountDTO,
     BindingResult result) {

         // validacion de errores
         if (result.hasFieldErrors()) {
             return validation(result);
         }

        

         // obtencion de roles
         Set<Role> roles = accountDTO.getRoles().stream()
         .map(role -> Role.builder()
             .name(RoleEnum.valueOf(role))
             .build())
             .collect(Collectors.toSet());


          // obtener roles 




         //  contruccion de accounbt
         Account account = Account.builder()
             .username(accountDTO.getEmail())
             .password( passwordEncoder.encode(accountDTO.getPassword() ) )
             .roles(roles)
             .build();


         return ResponseEntity.status(HttpStatus.CREATED)
                 .body( this.accountRepository.save(account) );


        

       
        
     }

    @DeleteMapping("/delete-user")
    public String deleteUser(@RequestParam Long id){
        this.accountRepository.deleteById(id);
        return "Se ha borrado la cuenta con el id "+ id;
    }
    

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " +
            err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
