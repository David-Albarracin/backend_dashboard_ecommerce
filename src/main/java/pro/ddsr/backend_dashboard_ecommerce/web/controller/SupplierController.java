
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

import pro.ddsr.backend_dashboard_ecommerce.domain.service.SupplierService;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Supplier;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Supplier> listSupplier(){
        return this.supplierService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Supplier> view(@PathVariable Long id){
        Optional<Supplier> optionalSupplier  = supplierService.findById(id);
        if (optionalSupplier.isPresent()){
            return ResponseEntity.ok(optionalSupplier.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Supplier supplier, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.save(supplier));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Supplier> update(@PathVariable Long id, @Valid @RequestBody Supplier supplier){
        Optional<Supplier> supplierOptional = this.supplierService.update(id, supplier);
        if (supplierOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(supplierOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Supplier> delete(@PathVariable Long id){
        //Supplier supplier = new Supplier();
        //supplier.setId(id);
        Optional<Supplier> optionalSupplier = this.supplierService.delete(id);
        if (optionalSupplier.isPresent()){
            return ResponseEntity.ok(optionalSupplier.orElseThrow());
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
