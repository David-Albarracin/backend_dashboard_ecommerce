
package pro.ddsr.backend_dashboard_ecommerce.web.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import pro.ddsr.backend_dashboard_ecommerce.domain.dto.AccountDto;
import pro.ddsr.backend_dashboard_ecommerce.domain.service.AccountService;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Account;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @GetMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Account> view(@PathVariable Long id){
        Optional<Account> optionalAccount  = accountService.findById(id);
        if (optionalAccount.isPresent()){
            return ResponseEntity.ok(optionalAccount.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody AccountDto account, BindingResult result){
        
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.save(account));
    }

    @PutMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Account> update(@PathVariable Long id, @Valid @RequestBody Account account){
        Optional<Account> accountOptional = this.accountService.update(id, account);
        if (accountOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(accountOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Account> delete(@PathVariable Long id){
        //Account account = new Account();
        //account.setId(id);
        Optional<Account> optionalAccount = this.accountService.delete(id);
        if (optionalAccount.isPresent()){
            return ResponseEntity.ok(optionalAccount.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
