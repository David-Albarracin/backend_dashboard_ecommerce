
package pro.ddsr.backend_dashboard_ecommerce.office.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import pro.ddsr.backend_dashboard_ecommerce.office.domain.dto.OfficeDto;
import pro.ddsr.backend_dashboard_ecommerce.office.domain.service.OfficeService;
import pro.ddsr.backend_dashboard_ecommerce.office.persistence.Office;

@RestController
@RequestMapping("/offices")
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    @GetMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public List<Office> listOffice(){
        return this.officeService.findAll();
    }

    @GetMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Office> view(@PathVariable Long id){
        Optional<Office> optionalOffice  = officeService.findById(id);
        if (optionalOffice.isPresent()){
            return ResponseEntity.ok(optionalOffice.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody OfficeDto office, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(officeService.save(office));
    }

    @PutMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Office> update(@PathVariable Long id, @Valid @RequestBody OfficeDto office){
        Optional<Office> officeOptional = this.officeService.update(id, office);
        if (officeOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(officeOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id){
        //Office office = new Office();
        //office.setId(id);
        try {
            Optional<Office> optionalOffice = this.officeService.delete(id);
            if (optionalOffice.isPresent()){
                return ResponseEntity.ok(optionalOffice.orElseThrow());
            }
            return ResponseEntity.notFound().build();

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No se puede eliminar una oficina que tiene empleados");
        }
        
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
