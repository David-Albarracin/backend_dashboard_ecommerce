
package pro.ddsr.backend_dashboard_ecommerce.office.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import pro.ddsr.backend_dashboard_ecommerce.office.domain.repository.OfficeRepository;
import pro.ddsr.backend_dashboard_ecommerce.office.persistence.Office;

@Service
public class OfficeService {
    // Define service methods here
    @Autowired
    OfficeRepository officeRepository;
    
    @Transactional
    public Optional<Office> delete(Long id) {
        Optional<Office> optionalOffice = this.officeRepository.findById(id);
        optionalOffice.ifPresent(
            OfficeFound -> {
                this.officeRepository.delete(OfficeFound);
            }
        );
        return optionalOffice;
    }
 
    public List<Office> findAll() {
        return (List<Office>) this.officeRepository.findAll();
    }

    public Optional<Office> findById(Long id) {
        return this.officeRepository.findById(id);
    }

    public Office save(Office Office) {
        return this.officeRepository.save(Office);
    }

    public Optional<Office> update(Long id, Office office) {
        Optional<Office> optionalOffice = this.officeRepository.findById(id);
        if (optionalOffice.isPresent()) {
            Office officeItem = optionalOffice.orElseThrow();
            //SETS
            
            return Optional.of(this.officeRepository.save(officeItem));
        }
        return optionalOffice;
    }
}
