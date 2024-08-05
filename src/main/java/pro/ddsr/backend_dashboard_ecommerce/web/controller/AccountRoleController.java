
package pro.ddsr.backend_dashboard_ecommerce.web.controller;

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

import pro.ddsr.backend_dashboard_ecommerce.domain.service.AccountRoleService;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.AccountRole;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/account_role")
public class AccountRoleController {

    @Autowired
    private AccountRoleService account_roleService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<AccountRole> listAccountRole(){
        return this.account_roleService.findAll();
    }

    @GetMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<AccountRole> view(@PathVariable Long id){
        Optional<AccountRole> optionalAccountRole  = account_roleService.findById(id);
        if (optionalAccountRole.isPresent()){
            return ResponseEntity.ok(optionalAccountRole.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody AccountRole account_role, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(account_roleService.save(account_role));
    }

    @PutMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<AccountRole> update(@PathVariable Long id, @Valid @RequestBody AccountRole account_role){
        Optional<AccountRole> account_roleOptional = this.account_roleService.update(id, account_role);
        if (account_roleOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(account_roleOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<AccountRole> delete(@PathVariable Long id){
        //AccountRole account_role = new AccountRole();
        //account_role.setId(id);
        Optional<AccountRole> optionalAccountRole = this.account_roleService.delete(id);
        if (optionalAccountRole.isPresent()){
            return ResponseEntity.ok(optionalAccountRole.orElseThrow());
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
