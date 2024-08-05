
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

import pro.ddsr.backend_dashboard_ecommerce.domain.service.RolePermissionService;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.RolePermission;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/role_permission")
public class RolePermissionController {

    @Autowired
    private RolePermissionService role_permissionService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<RolePermission> listRolePermission(){
        return this.role_permissionService.findAll();
    }

    @GetMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<RolePermission> view(@PathVariable Long id){
        Optional<RolePermission> optionalRolePermission  = role_permissionService.findById(id);
        if (optionalRolePermission.isPresent()){
            return ResponseEntity.ok(optionalRolePermission.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody RolePermission role_permission, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(role_permissionService.save(role_permission));
    }

    @PutMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<RolePermission> update(@PathVariable Long id, @Valid @RequestBody RolePermission role_permission){
        Optional<RolePermission> role_permissionOptional = this.role_permissionService.update(id, role_permission);
        if (role_permissionOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(role_permissionOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<RolePermission> delete(@PathVariable Long id){
        //RolePermission role_permission = new RolePermission();
        //role_permission.setId(id);
        Optional<RolePermission> optionalRolePermission = this.role_permissionService.delete(id);
        if (optionalRolePermission.isPresent()){
            return ResponseEntity.ok(optionalRolePermission.orElseThrow());
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
