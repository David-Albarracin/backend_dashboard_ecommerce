
package backend_dashboard_ecommerce.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend_dashboard_ecommerce.domain.service.AccountService;
import backend_dashboard_ecommerce.persistence.entity.Account;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Account> listAccount(){
        return this.accountService.findAll();
    }

    @GetMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Account> view(@PathVariable Long id){
        Optional<Account> optionalAccount  = accountService.findById(id);
        if (optionalAccount.isPresent()){
            return ResponseEntity.ok(optionalAccount.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Account account, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.save(account));
    }

    @PutMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Account> update(@PathVariable Long id, @Valid @RequestBody Account account){
        Optional<Account> accountOptional = this.accountService.update(id, account);
        if (accountOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(accountOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('DELETE')")
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
