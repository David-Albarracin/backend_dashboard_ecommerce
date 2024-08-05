
package pro.ddsr.backend_dashboard_ecommerce.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backend_dashboard_ecommerce.domain.repository.OfficePhoneRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.OfficePhone;

@Service
public class OfficePhoneService {
    // Define service methods here
    @Autowired
    OfficePhoneRepository office_phoneRepository;
    
    @Transactional
    public Optional<OfficePhone> delete(Long id) {
        Optional<OfficePhone> optionalOfficePhone = this.office_phoneRepository.findById(id);
        optionalOfficePhone.ifPresent(
            OfficePhoneFound -> {
                this.office_phoneRepository.delete(OfficePhoneFound);
            }
        );
        return optionalOfficePhone;
    }
 
    public List<OfficePhone> findAll() {
        return (List<OfficePhone>) this.office_phoneRepository.findAll();
    }

    public Optional<OfficePhone> findById(Long id) {
        return this.office_phoneRepository.findById(id);
    }

    public OfficePhone save(OfficePhone OfficePhone) {
        return this.office_phoneRepository.save(OfficePhone);
    }

    public Optional<OfficePhone> update(Long id, OfficePhone office_phone) {
        Optional<OfficePhone> optionalOfficePhone = this.office_phoneRepository.findById(id);
        if (optionalOfficePhone.isPresent()) {
            OfficePhone office_phoneItem = optionalOfficePhone.orElseThrow();
            //SETS
            
            return Optional.of(this.office_phoneRepository.save(office_phoneItem));
        }
        return optionalOfficePhone;
    }
}
