
package pro.ddsr.backend_dashboard_ecommerce.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backend_dashboard_ecommerce.domain.repository.CustomerPhoneRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.CustomerPhone;

@Service
public class CustomerPhoneService {
    // Define service methods here
    @Autowired
    CustomerPhoneRepository customer_phoneRepository;
    
    @Transactional
    public Optional<CustomerPhone> delete(Long id) {
        Optional<CustomerPhone> optionalCustomerPhone = this.customer_phoneRepository.findById(id);
        optionalCustomerPhone.ifPresent(
            CustomerPhoneFound -> {
                this.customer_phoneRepository.delete(CustomerPhoneFound);
            }
        );
        return optionalCustomerPhone;
    }
 
    public List<CustomerPhone> findAll() {
        return (List<CustomerPhone>) this.customer_phoneRepository.findAll();
    }

    public Optional<CustomerPhone> findById(Long id) {
        return this.customer_phoneRepository.findById(id);
    }

    public CustomerPhone save(CustomerPhone CustomerPhone) {
        return this.customer_phoneRepository.save(CustomerPhone);
    }

    public Optional<CustomerPhone> update(Long id, CustomerPhone customer_phone) {
        Optional<CustomerPhone> optionalCustomerPhone = this.customer_phoneRepository.findById(id);
        if (optionalCustomerPhone.isPresent()) {
            CustomerPhone customer_phoneItem = optionalCustomerPhone.orElseThrow();
            //SETS
            
            return Optional.of(this.customer_phoneRepository.save(customer_phoneItem));
        }
        return optionalCustomerPhone;
    }
}
