
package pro.ddsr.backend_dashboard_ecommerce.role.web;

import java.util.HashMap;
import java.util.List;
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
import pro.ddsr.backend_dashboard_ecommerce.role.domain.service.RoleService;
import pro.ddsr.backend_dashboard_ecommerce.role.persistence.Role;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public List<Role> listRole(){
        return this.roleService.findAll();
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Role> view(@PathVariable Long id){
        Optional<Role> optionalRole  = roleService.findById(id);
        if (optionalRole.isPresent()){
            return ResponseEntity.ok(optionalRole.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody Role role, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.save(role));
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Role> update(@PathVariable Long id, @Valid @RequestBody Role role){
        Optional<Role> roleOptional = this.roleService.update(id, role);
        if (roleOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(roleOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Role> delete(@PathVariable Long id){
        //Role role = new Role();
        //role.setId(id);
        Optional<Role> optionalRole = this.roleService.delete(id);
        if (optionalRole.isPresent()){
            return ResponseEntity.ok(optionalRole.orElseThrow());
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
