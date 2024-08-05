
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

import pro.ddsr.backend_dashboard_ecommerce.domain.service.SupplierPhoneService;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.SupplierPhone;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/supplier_phones")
public class SupplierPhoneController {

    @Autowired
    private SupplierPhoneService supplier_phoneService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<SupplierPhone> listSupplierPhone(){
        return this.supplier_phoneService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<SupplierPhone> view(@PathVariable Long id){
        Optional<SupplierPhone> optionalSupplierPhone  = supplier_phoneService.findById(id);
        if (optionalSupplierPhone.isPresent()){
            return ResponseEntity.ok(optionalSupplierPhone.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody SupplierPhone supplier_phone, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(supplier_phoneService.save(supplier_phone));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<SupplierPhone> update(@PathVariable Long id, @Valid @RequestBody SupplierPhone supplier_phone){
        Optional<SupplierPhone> supplier_phoneOptional = this.supplier_phoneService.update(id, supplier_phone);
        if (supplier_phoneOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(supplier_phoneOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<SupplierPhone> delete(@PathVariable Long id){
        //SupplierPhone supplier_phone = new SupplierPhone();
        //supplier_phone.setId(id);
        Optional<SupplierPhone> optionalSupplierPhone = this.supplier_phoneService.delete(id);
        if (optionalSupplierPhone.isPresent()){
            return ResponseEntity.ok(optionalSupplierPhone.orElseThrow());
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
