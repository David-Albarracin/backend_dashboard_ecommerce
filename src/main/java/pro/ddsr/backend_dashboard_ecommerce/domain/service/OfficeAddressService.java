
package pro.ddsr.backend_dashboard_ecommerce.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backend_dashboard_ecommerce.domain.repository.OfficeAddressRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.OfficeAddress;

@Service
public class OfficeAddressService {
    // Define service methods here
    @Autowired
    OfficeAddressRepository office_addressRepository;
    
    @Transactional
    public Optional<OfficeAddress> delete(Long id) {
        Optional<OfficeAddress> optionalOfficeAddress = this.office_addressRepository.findById(id);
        optionalOfficeAddress.ifPresent(
            OfficeAddressFound -> {
                this.office_addressRepository.delete(OfficeAddressFound);
            }
        );
        return optionalOfficeAddress;
    }
 
    public List<OfficeAddress> findAll() {
        return (List<OfficeAddress>) this.office_addressRepository.findAll();
    }

    public Optional<OfficeAddress> findById(Long id) {
        return this.office_addressRepository.findById(id);
    }

    public OfficeAddress save(OfficeAddress OfficeAddress) {
        return this.office_addressRepository.save(OfficeAddress);
    }

    public Optional<OfficeAddress> update(Long id, OfficeAddress office_address) {
        Optional<OfficeAddress> optionalOfficeAddress = this.office_addressRepository.findById(id);
        if (optionalOfficeAddress.isPresent()) {
            OfficeAddress office_addressItem = optionalOfficeAddress.orElseThrow();
            //SETS
            
            return Optional.of(this.office_addressRepository.save(office_addressItem));
        }
        return optionalOfficeAddress;
    }
}
