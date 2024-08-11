
package pro.ddsr.backend_dashboard_ecommerce.payMethod.web;

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
import pro.ddsr.backend_dashboard_ecommerce.payMethod.domain.service.PayMethodService;
import pro.ddsr.backend_dashboard_ecommerce.payMethod.persistence.PayMethod;

@RestController
@RequestMapping("/pay_methods")
public class PayMethodController {

    @Autowired
    private PayMethodService pay_methodService;

    @GetMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public List<PayMethod> listPayMethod(){
        return this.pay_methodService.findAll();
    }

    @GetMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PayMethod> view(@PathVariable Long id){
        Optional<PayMethod> optionalPayMethod  = pay_methodService.findById(id);
        if (optionalPayMethod.isPresent()){
            return ResponseEntity.ok(optionalPayMethod.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody PayMethod pay_method, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(pay_methodService.save(pay_method));
    }

    @PutMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PayMethod> update(@PathVariable Long id, @Valid @RequestBody PayMethod pay_method){
        Optional<PayMethod> pay_methodOptional = this.pay_methodService.update(id, pay_method);
        if (pay_methodOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(pay_methodOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PayMethod> delete(@PathVariable Long id){
        //PayMethod pay_method = new PayMethod();
        //pay_method.setId(id);
        Optional<PayMethod> optionalPayMethod = this.pay_methodService.delete(id);
        if (optionalPayMethod.isPresent()){
            return ResponseEntity.ok(optionalPayMethod.orElseThrow());
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
