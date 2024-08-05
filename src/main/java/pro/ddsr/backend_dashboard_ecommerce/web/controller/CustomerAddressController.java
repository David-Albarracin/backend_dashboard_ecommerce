
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

import pro.ddsr.backend_dashboard_ecommerce.domain.service.CustomerAddressService;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.CustomerAddress;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer_address")
public class CustomerAddressController {

    @Autowired
    private CustomerAddressService customer_addressService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<CustomerAddress> listCustomerAddress(){
        return this.customer_addressService.findAll();
    }

    @GetMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<CustomerAddress> view(@PathVariable Long id){
        Optional<CustomerAddress> optionalCustomerAddress  = customer_addressService.findById(id);
        if (optionalCustomerAddress.isPresent()){
            return ResponseEntity.ok(optionalCustomerAddress.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody CustomerAddress customer_address, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(customer_addressService.save(customer_address));
    }

    @PutMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<CustomerAddress> update(@PathVariable Long id, @Valid @RequestBody CustomerAddress customer_address){
        Optional<CustomerAddress> customer_addressOptional = this.customer_addressService.update(id, customer_address);
        if (customer_addressOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(customer_addressOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<CustomerAddress> delete(@PathVariable Long id){
        //CustomerAddress customer_address = new CustomerAddress();
        //customer_address.setId(id);
        Optional<CustomerAddress> optionalCustomerAddress = this.customer_addressService.delete(id);
        if (optionalCustomerAddress.isPresent()){
            return ResponseEntity.ok(optionalCustomerAddress.orElseThrow());
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
