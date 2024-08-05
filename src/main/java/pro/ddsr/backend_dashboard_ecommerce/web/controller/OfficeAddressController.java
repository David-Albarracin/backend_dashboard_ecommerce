
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

import pro.ddsr.backend_dashboard_ecommerce.domain.service.OfficeAddressService;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.OfficeAddress;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/office_address")
public class OfficeAddressController {

    @Autowired
    private OfficeAddressService office_addressService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<OfficeAddress> listOfficeAddress(){
        return this.office_addressService.findAll();
    }

    @GetMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<OfficeAddress> view(@PathVariable Long id){
        Optional<OfficeAddress> optionalOfficeAddress  = office_addressService.findById(id);
        if (optionalOfficeAddress.isPresent()){
            return ResponseEntity.ok(optionalOfficeAddress.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody OfficeAddress office_address, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(office_addressService.save(office_address));
    }

    @PutMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<OfficeAddress> update(@PathVariable Long id, @Valid @RequestBody OfficeAddress office_address){
        Optional<OfficeAddress> office_addressOptional = this.office_addressService.update(id, office_address);
        if (office_addressOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(office_addressOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<OfficeAddress> delete(@PathVariable Long id){
        //OfficeAddress office_address = new OfficeAddress();
        //office_address.setId(id);
        Optional<OfficeAddress> optionalOfficeAddress = this.office_addressService.delete(id);
        if (optionalOfficeAddress.isPresent()){
            return ResponseEntity.ok(optionalOfficeAddress.orElseThrow());
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
