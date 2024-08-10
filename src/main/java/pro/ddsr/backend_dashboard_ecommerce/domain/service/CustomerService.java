
package pro.ddsr.backend_dashboard_ecommerce.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import pro.ddsr.backend_dashboard_ecommerce.domain.dto.customerDto.CustomerAddressDto;
import pro.ddsr.backend_dashboard_ecommerce.domain.dto.customerDto.CustomerDto;
import pro.ddsr.backend_dashboard_ecommerce.domain.dto.customerDto.CustomerPhoneDto;
import pro.ddsr.backend_dashboard_ecommerce.domain.repository.CustomerRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Customer;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.CustomerAddress;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.CustomerPhone;

@Service
public class CustomerService {
    // Define service methods here
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerPhoneService customerPhoneService;

    
    
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

    public List<Customer> findCustomersByCity(Long cityId) {
        return this.customerRepository.findCustomersByCity(cityId);
    }

    public List<Customer> findCustomersWithPendingOrders(Long statusId) {
        return customerRepository.findCustomersWithPendingOrders(statusId);
    }

    public Customer save(CustomerDto dto) {
        Customer customer = CustomerDto.toEntity(dto);
        return this.customerRepository.save(customer);
    }

    public Optional<Customer> update(Long id, CustomerDto customer) {
        Optional<Customer> optionalCustomer = this.customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            // construir nueva entidad
            Customer updateCustomer = CustomerDto.toEntity(customer);

            // convertir direcciones de dto en entidades
            Set<CustomerAddress> addresses = customer.getAddresses().stream()
            .map(address -> {
                address.setCustomerId(id);
                CustomerAddress newAddress =  CustomerAddressDto.toEntity(address);
                return newAddress;
            })
            .collect(Collectors.toSet());
            
            // convertir telefonos de dto en entidades
            Set<CustomerPhone> phones = customer.getPhones().stream()
                .map( phone -> {
                    phone.setCustomerId(id);
                    CustomerPhone newPhone = CustomerPhoneDto.toEntity(phone);
                    return newPhone;
                })
                .collect(Collectors.toSet());

            // seteando telefonos y direcciones
            updateCustomer.setAddresses(addresses);
            updateCustomer.setPhones(phones);
            
            return Optional.of(this.customerRepository.save(updateCustomer));
        }
        return optionalCustomer;
    }
}
