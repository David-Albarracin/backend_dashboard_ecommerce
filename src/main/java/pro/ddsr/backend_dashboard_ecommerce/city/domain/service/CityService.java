
package pro.ddsr.backend_dashboard_ecommerce.city.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import pro.ddsr.backend_dashboard_ecommerce.city.domain.repository.CityRepository;
import pro.ddsr.backend_dashboard_ecommerce.city.persistence.City;

@Service
public class CityService {
    // Define service methods here
    @Autowired
    CityRepository cityRepository;
    
    @Transactional
    public Optional<City> delete(Long id) {
        Optional<City> optionalCity = this.cityRepository.findById(id);
        optionalCity.ifPresent(
            CityFound -> {
                this.cityRepository.delete(CityFound);
            }
        );
        return optionalCity;
    }
 
    public List<City> findAll() {
        return (List<City>) this.cityRepository.findAll();
    }

    public Optional<City> findById(Long id) {
        return this.cityRepository.findById(id);
    }

    public City save(City City) {
        return this.cityRepository.save(City);
    }

    public Optional<City> update(Long id, City city) {
        Optional<City> optionalCity = this.cityRepository.findById(id);
        if (optionalCity.isPresent()) {
            City cityItem = optionalCity.orElseThrow();
            //SETS
            
            return Optional.of(this.cityRepository.save(cityItem));
        }
        return optionalCity;
    }
}
