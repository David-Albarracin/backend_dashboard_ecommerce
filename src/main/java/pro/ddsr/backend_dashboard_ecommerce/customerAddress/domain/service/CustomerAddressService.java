
package pro.ddsr.backend_dashboard_ecommerce.customerAddress.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import pro.ddsr.backend_dashboard_ecommerce.customerAddress.domain.dto.CustomerAddressDto;
import pro.ddsr.backend_dashboard_ecommerce.customerAddress.domain.repository.CustomerAddressRepository;
import pro.ddsr.backend_dashboard_ecommerce.customerAddress.persistence.CustomerAddress;

@Service
public class CustomerAddressService {
    // Define service methods here
    @Autowired
    CustomerAddressRepository customer_addressRepository;
    
    @Transactional
    public Optional<CustomerAddress> delete(Long id) {
        Optional<CustomerAddress> optionalCustomerAddress = this.customer_addressRepository.findById(id);
        optionalCustomerAddress.ifPresent(
            CustomerAddressFound -> {
                this.customer_addressRepository.delete(CustomerAddressFound);
            }
        );
        return optionalCustomerAddress;
    }
 
    public List<CustomerAddress> findAll() {
        return (List<CustomerAddress>) this.customer_addressRepository.findAll();
    }

    public Optional<CustomerAddress> findById(Long id) {
        return this.customer_addressRepository.findById(id);
    }

    public void saveAll(Set<CustomerAddressDto> dtos, Long customerId) {
        
        // obtener direcciones
        Set<CustomerAddress> addresses = dtos.stream()
            .map(address -> {
                address.setCustomerId(customerId);
                CustomerAddress newAddress =  CustomerAddressDto.toEntity(address);
                return newAddress;
            })
            .collect(Collectors.toSet());
            
        // guardar todos
        this.customer_addressRepository.saveAll(addresses);
    }

    public Optional<CustomerAddress> update(Long id, CustomerAddress customer_address) {
        Optional<CustomerAddress> optionalCustomerAddress = this.customer_addressRepository.findById(id);
        if (optionalCustomerAddress.isPresent()) {
            CustomerAddress customer_addressItem = optionalCustomerAddress.orElseThrow();
            //SETS
            
            return Optional.of(this.customer_addressRepository.save(customer_addressItem));
        }
        return optionalCustomerAddress;
    }
}
