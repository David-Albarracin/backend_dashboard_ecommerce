
package pro.ddsr.backend_dashboard_ecommerce.customer.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pro.ddsr.backend_dashboard_ecommerce.customer.domain.dto.CustomerDto;
import pro.ddsr.backend_dashboard_ecommerce.customer.domain.dto.CustomerProjection;
import pro.ddsr.backend_dashboard_ecommerce.customer.domain.repository.CustomerRepository;
import pro.ddsr.backend_dashboard_ecommerce.customer.persistence.Customer;
import pro.ddsr.backend_dashboard_ecommerce.customerAddress.domain.dto.CustomerAddressDto;
import pro.ddsr.backend_dashboard_ecommerce.customerAddress.domain.repository.CustomerAddressRepository;
import pro.ddsr.backend_dashboard_ecommerce.customerAddress.persistence.CustomerAddress;
import pro.ddsr.backend_dashboard_ecommerce.customerPhone.domain.dto.CustomerPhoneDto;
import pro.ddsr.backend_dashboard_ecommerce.customerPhone.domain.repository.CustomerPhoneRepository;
import pro.ddsr.backend_dashboard_ecommerce.customerPhone.persistence.CustomerPhone;

@Service
public class CustomerService {
    // Define service methods here
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerPhoneRepository customerPhoneRepository;

    @Autowired
    CustomerAddressRepository customerAddressRepository;

    
    
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
 
    public List<CustomerProjection> findAll() {
        return  this.customerRepository.findAlProjections();
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

    public Optional<Customer> update(Long id, CustomerDto dto) {
        Optional<Customer> optionalCustomer = this.customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            // construir nueva entidad
            dto.setCustomerId(id); // solo por si acaso xd
            Customer updateCustomer = this.createNewCustomer(dto);
            return Optional.of(updateCustomer);

            
        }
        return optionalCustomer;
    }


     /*
     * Parsea un dto a una Orden  y sus detalles, y lo guarda
    */
    public Customer createNewCustomer(CustomerDto customerDto){
        
       // convertir el dto de cliente y guardar
       Customer newCustomer = this.customerRepository.save(CustomerDto.toEntity(customerDto));
        

        // convertir las direcciones 
        Set<CustomerAddress> addresses = customerDto.getAddresses().stream()
            .map( dto -> {
                dto.setCustomerId( newCustomer.getCustomerId());
                return CustomerAddressDto.toEntity(dto);
            })
            .collect( Collectors.toSet());

        // seteando y guardando
        
        this.customerAddressRepository.saveAll(addresses);
            


        // convertir los telefonos
        Set<CustomerPhone> phones = customerDto.getPhones().stream()
            .map( dto -> {
                dto.setCustomerId( newCustomer.getCustomerId());
                return CustomerPhoneDto.toEntity(dto);
            })
            .collect( Collectors.toSet());
        
        // seteando y guardando
        this.customerPhoneRepository.saveAll(phones);

        // SETS
        newCustomer.setAddresses(addresses);
        newCustomer.setPhones(phones);

        return newCustomer;

    }
}
