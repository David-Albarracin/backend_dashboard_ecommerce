
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

import pro.ddsr.backend_dashboard_ecommerce.domain.service.ChargeService;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Charge;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/charges")
public class ChargeController {

    @Autowired
    private ChargeService chargeService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Charge> listCharge(){
        return this.chargeService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Charge> view(@PathVariable Long id){
        Optional<Charge> optionalCharge  = chargeService.findById(id);
        if (optionalCharge.isPresent()){
            return ResponseEntity.ok(optionalCharge.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Charge charge, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(chargeService.save(charge));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Charge> update(@PathVariable Long id, @Valid @RequestBody Charge charge){
        Optional<Charge> chargeOptional = this.chargeService.update(id, charge);
        if (chargeOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(chargeOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Charge> delete(@PathVariable Long id){
        //Charge charge = new Charge();
        //charge.setId(id);
        Optional<Charge> optionalCharge = this.chargeService.delete(id);
        if (optionalCharge.isPresent()){
            return ResponseEntity.ok(optionalCharge.orElseThrow());
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
