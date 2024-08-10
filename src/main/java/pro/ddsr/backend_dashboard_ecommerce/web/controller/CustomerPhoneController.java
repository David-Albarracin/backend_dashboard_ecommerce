
package pro.ddsr.backend_dashboard_ecommerce.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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

import pro.ddsr.backend_dashboard_ecommerce.domain.dto.customerDto.CustomerPhoneDto;
import pro.ddsr.backend_dashboard_ecommerce.domain.service.CustomerPhoneService;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.CustomerPhone;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer_phones")
public class CustomerPhoneController {

    @Autowired
    private CustomerPhoneService customer_phoneService;

    @GetMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public List<CustomerPhone> listCustomerPhone(){
        return this.customer_phoneService.findAll();
    }

    @GetMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomerPhone> view(@PathVariable Long id){
        Optional<CustomerPhone> optionalCustomerPhone  = customer_phoneService.findById(id);
        if (optionalCustomerPhone.isPresent()){
            return ResponseEntity.ok(optionalCustomerPhone.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody Set<CustomerPhoneDto> customer_phone, BindingResult result, Long customerId){
        if (result.hasFieldErrors()) {
            return validation(result);
        }

        this.customer_phoneService.saveAll(customer_phone, customerId);

        return ResponseEntity.status(HttpStatus.CREATED).body("Creados correctamente");
    }

    @PutMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomerPhone> update(@PathVariable Long id, @Valid @RequestBody CustomerPhone customer_phone){
        Optional<CustomerPhone> customer_phoneOptional = this.customer_phoneService.update(id, customer_phone);
        if (customer_phoneOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(customer_phoneOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomerPhone> delete(@PathVariable Long id){
        //CustomerPhone customer_phone = new CustomerPhone();
        //customer_phone.setId(id);
        Optional<CustomerPhone> optionalCustomerPhone = this.customer_phoneService.delete(id);
        if (optionalCustomerPhone.isPresent()){
            return ResponseEntity.ok(optionalCustomerPhone.orElseThrow());
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
