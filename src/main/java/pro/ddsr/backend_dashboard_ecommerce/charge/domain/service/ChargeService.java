
package pro.ddsr.backend_dashboard_ecommerce.charge.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import pro.ddsr.backend_dashboard_ecommerce.charge.domain.repository.ChargeRepository;
import pro.ddsr.backend_dashboard_ecommerce.charge.persistence.Charge;

@Service
public class ChargeService {
    // Define service methods here
    @Autowired
    ChargeRepository chargeRepository;
    
    @Transactional
    public Optional<Charge> delete(Long id) {
        Optional<Charge> optionalCharge = this.chargeRepository.findById(id);
        optionalCharge.ifPresent(
            ChargeFound -> {
                this.chargeRepository.delete(ChargeFound);
            }
        );
        return optionalCharge;
    }
 
    public List<Charge> findAll() {
        return (List<Charge>) this.chargeRepository.findAll();
    }

    public Optional<Charge> findById(Long id) {
        return this.chargeRepository.findById(id);
    }

    public Charge save(Charge Charge) {
        return this.chargeRepository.save(Charge);
    }

    public Optional<Charge> update(Long id, Charge charge) {
        Optional<Charge> optionalCharge = this.chargeRepository.findById(id);
        if (optionalCharge.isPresent()) {
            Charge chargeItem = optionalCharge.orElseThrow();
            //SETS
            
            return Optional.of(this.chargeRepository.save(chargeItem));
        }
        return optionalCharge;
    }
}
