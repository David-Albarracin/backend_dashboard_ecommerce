
package pro.ddsr.backend_dashboard_ecommerce.office.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import pro.ddsr.backend_dashboard_ecommerce.city.domain.repository.CityRepository;
import pro.ddsr.backend_dashboard_ecommerce.city.persistence.City;
import pro.ddsr.backend_dashboard_ecommerce.office.domain.dto.OfficeDto;
import pro.ddsr.backend_dashboard_ecommerce.office.domain.repository.OfficeRepository;
import pro.ddsr.backend_dashboard_ecommerce.office.persistence.Office;

@Service
public class OfficeService {
    // Define service methods here
    @Autowired
    OfficeRepository officeRepository;

    @Autowired
    CityRepository cityRepository;
    
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

    public Office save(OfficeDto office) {
        Office officeItem = office.toOffice(cityRepository.findById(office.getCity()).get());
        return this.officeRepository.save(officeItem);
    }

    public Optional<Office> update(Long id, OfficeDto officeDto) {
        Optional<Office> optionalOffice = this.officeRepository.findById(id);
        
        if (!optionalOffice.isPresent()) {
            return Optional.empty();
        }

        City city = cityRepository.findById(officeDto.getCity())
            .orElseThrow(() -> new IllegalArgumentException("City no encontrada"));

        Office officeItem = officeDto.toOffice(city);
        officeItem.setOfficeId(id);

        try {
            return Optional.of(this.officeRepository.save(officeItem));
        } catch (DataIntegrityViolationException e) {
            // Manejar conflicto de integridad de datos (clave única)
            return Optional.empty();
        }
    }
}
