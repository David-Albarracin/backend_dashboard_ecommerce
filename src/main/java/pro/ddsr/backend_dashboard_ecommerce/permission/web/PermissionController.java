
package pro.ddsr.backend_dashboard_ecommerce.permission.web;

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
import pro.ddsr.backend_dashboard_ecommerce.permission.domain.service.PermissionService;
import pro.ddsr.backend_dashboard_ecommerce.permission.persistence.Permission;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public List<Permission> listPermission(){
        return this.permissionService.findAll();
    }

    @GetMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Permission> view(@PathVariable Long id){
        Optional<Permission> optionalPermission  = permissionService.findById(id);
        if (optionalPermission.isPresent()){
            return ResponseEntity.ok(optionalPermission.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody Permission permission, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(permissionService.save(permission));
    }

    @PutMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Permission> update(@PathVariable Long id, @Valid @RequestBody Permission permission){
        Optional<Permission> permissionOptional = this.permissionService.update(id, permission);
        if (permissionOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(permissionOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Permission> delete(@PathVariable Long id){
        //Permission permission = new Permission();
        //permission.setId(id);
        Optional<Permission> optionalPermission = this.permissionService.delete(id);
        if (optionalPermission.isPresent()){
            return ResponseEntity.ok(optionalPermission.orElseThrow());
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
