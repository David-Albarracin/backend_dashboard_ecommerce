
package pro.ddsr.backend_dashboard_ecommerce.region.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import pro.ddsr.backend_dashboard_ecommerce.region.domain.repository.RegionRepository;
import pro.ddsr.backend_dashboard_ecommerce.region.persistence.Region;

@Service
public class RegionService {
    // Define service methods here
    @Autowired
    RegionRepository regionRepository;
    
    @Transactional
    public Optional<Region> delete(Long id) {
        Optional<Region> optionalRegion = this.regionRepository.findById(id);
        optionalRegion.ifPresent(
            RegionFound -> {
                this.regionRepository.delete(RegionFound);
            }
        );
        return optionalRegion;
    }
 
    public List<Region> findAll() {
        return (List<Region>) this.regionRepository.findAll();
    }

    public Optional<Region> findById(Long id) {
        return this.regionRepository.findById(id);
    }

    public Region save(Region Region) {
        return this.regionRepository.save(Region);
    }

    public Optional<Region> update(Long id, Region region) {
        Optional<Region> optionalRegion = this.regionRepository.findById(id);
        if (optionalRegion.isPresent()) {
            Region regionItem = optionalRegion.orElseThrow();
            //SETS
            
            return Optional.of(this.regionRepository.save(regionItem));
        }
        return optionalRegion;
    }
}
