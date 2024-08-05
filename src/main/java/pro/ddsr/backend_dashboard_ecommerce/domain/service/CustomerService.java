
package pro.ddsr.backend_dashboard_ecommerce.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backend_dashboard_ecommerce.domain.repository.CustomerRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Customer;

@Service
public class CustomerService {
    // Define service methods here
    @Autowired
    CustomerRepository customerRepository;
    
    @Transactional
    public Optional<Customer> delete(Long id) {
        Optional<Customer> optionalCustomer = this.customerRepository.findById(id);
        optionalCustomer.ifPresent(
            CustomerFound -> {
                this.customerRepository.delete(CustomerFound);
            }
        );
        return optionalCustomer;
    }
 
    public List<Customer> findAll() {
        return (List<Customer>) this.customerRepository.findAll();
    }

    public Optional<Customer> findById(Long id) {
        return this.customerRepository.findById(id);
    }

    public Customer save(Customer Customer) {
        return this.customerRepository.save(Customer);
    }

    public Optional<Customer> update(Long id, Customer customer) {
        Optional<Customer> optionalCustomer = this.customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customerItem = optionalCustomer.orElseThrow();
            //SETS
            
            return Optional.of(this.customerRepository.save(customerItem));
        }
        return optionalCustomer;
    }
}
