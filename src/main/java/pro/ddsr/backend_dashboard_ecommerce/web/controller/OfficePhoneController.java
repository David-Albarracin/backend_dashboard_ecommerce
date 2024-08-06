
package pro.ddsr.backend_dashboard_ecommerce.web.controller;

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

import pro.ddsr.backend_dashboard_ecommerce.domain.service.OfficePhoneService;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.OfficePhone;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/office_phones")
public class OfficePhoneController {

    @Autowired
    private OfficePhoneService office_phoneService;

    @GetMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public List<OfficePhone> listOfficePhone(){
        return this.office_phoneService.findAll();
    }

    @GetMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OfficePhone> view(@PathVariable Long id){
        Optional<OfficePhone> optionalOfficePhone  = office_phoneService.findById(id);
        if (optionalOfficePhone.isPresent()){
            return ResponseEntity.ok(optionalOfficePhone.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody OfficePhone office_phone, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(office_phoneService.save(office_phone));
    }

    @PutMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OfficePhone> update(@PathVariable Long id, @Valid @RequestBody OfficePhone office_phone){
        Optional<OfficePhone> office_phoneOptional = this.office_phoneService.update(id, office_phone);
        if (office_phoneOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(office_phoneOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OfficePhone> delete(@PathVariable Long id){
        //OfficePhone office_phone = new OfficePhone();
        //office_phone.setId(id);
        Optional<OfficePhone> optionalOfficePhone = this.office_phoneService.delete(id);
        if (optionalOfficePhone.isPresent()){
            return ResponseEntity.ok(optionalOfficePhone.orElseThrow());
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
