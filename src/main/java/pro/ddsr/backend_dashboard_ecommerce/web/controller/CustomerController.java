
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

import pro.ddsr.backend_dashboard_ecommerce.domain.dto.customerDto.CustomerDto;
import pro.ddsr.backend_dashboard_ecommerce.domain.service.CustomerAddressService;
import pro.ddsr.backend_dashboard_ecommerce.domain.service.CustomerPhoneService;
import pro.ddsr.backend_dashboard_ecommerce.domain.service.CustomerService;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Customer;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired 
    private CustomerPhoneService customerPhoneService;

    @Autowired
    private CustomerAddressService customerAddressService;

    @GetMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public List<Customer> listCustomer(){
        return this.customerService.findAll();
    }

    @GetMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Customer> view(@PathVariable Long id){
        Optional<Customer> optionalCustomer = customerService.findById(id);
        if (optionalCustomer.isPresent()){
            return ResponseEntity.ok(optionalCustomer.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // CU8
    @GetMapping("/bycity/{cityId}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Customer>> viewByCity(@PathVariable Long cityId){
        System.out.println(cityId);
        List<Customer> listCustomer = customerService.findCustomersByCity(cityId);
        if (listCustomer.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listCustomer);
    }

    // CU15
    @GetMapping("/bystatus/{statusId}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Customer>> viewByPendingOrders(@PathVariable Long statusId){
        List<Customer> listCustomer = customerService.findCustomersWithPendingOrders(statusId);
        if (listCustomer.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listCustomer);
    }

    @PostMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody CustomerDto customer, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Customer newCustomer = this.customerService.save(customer);

        this.customerAddressService.saveAll( customer.getAddresses(), newCustomer.getCustomerId());
        this.customerPhoneService.saveAll( customer.getPhones(), newCustomer.getCustomerId());

        return ResponseEntity.status(HttpStatus.CREATED).body(newCustomer);
    }

    @PutMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Customer> update(@PathVariable Long id, @Valid @RequestBody CustomerDto customer){
        Optional<Customer> customerOptional = this.customerService.update(id, customer);
        if (customerOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(customerOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Customer> delete(@PathVariable Long id){
        //Customer customer = new Customer();
        //customer.setId(id);
        Optional<Customer> optionalCustomer = this.customerService.delete(id);
        if (optionalCustomer.isPresent()){
            return ResponseEntity.ok(optionalCustomer.orElseThrow());
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
