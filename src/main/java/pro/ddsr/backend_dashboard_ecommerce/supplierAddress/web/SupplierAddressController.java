
package pro.ddsr.backend_dashboard_ecommerce.supplierAddress.web;

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
import pro.ddsr.backend_dashboard_ecommerce.supplierAddress.domain.service.SupplierAddressService;
import pro.ddsr.backend_dashboard_ecommerce.supplierAddress.persistence.SupplierAddress;

@RestController
@RequestMapping("/supplier_addresses")
public class SupplierAddressController {

    @Autowired
    private SupplierAddressService supplier_addressService;

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public List<SupplierAddress> listSupplierAddress(){
        return this.supplier_addressService.findAll();
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SupplierAddress> view(@PathVariable Long id){
        Optional<SupplierAddress> optionalSupplierAddress  = supplier_addressService.findById(id);
        if (optionalSupplierAddress.isPresent()){
            return ResponseEntity.ok(optionalSupplierAddress.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody SupplierAddress supplier_address, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(supplier_addressService.save(supplier_address));
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SupplierAddress> update(@PathVariable Long id, @Valid @RequestBody SupplierAddress supplier_address){
        Optional<SupplierAddress> supplier_addressOptional = this.supplier_addressService.update(id, supplier_address);
        if (supplier_addressOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(supplier_addressOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SupplierAddress> delete(@PathVariable Long id){
        //SupplierAddress supplier_address = new SupplierAddress();
        //supplier_address.setId(id);
        Optional<SupplierAddress> optionalSupplierAddress = this.supplier_addressService.delete(id);
        if (optionalSupplierAddress.isPresent()){
            return ResponseEntity.ok(optionalSupplierAddress.orElseThrow());
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
