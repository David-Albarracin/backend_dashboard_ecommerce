
package pro.ddsr.backend_dashboard_ecommerce.payMethod.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import pro.ddsr.backend_dashboard_ecommerce.payMethod.domain.repository.PayMethodRepository;
import pro.ddsr.backend_dashboard_ecommerce.payMethod.persistence.PayMethod;

@Service
public class PayMethodService {
    // Define service methods here
    @Autowired
    PayMethodRepository pay_methodRepository;
    
    @Transactional
    public Optional<PayMethod> delete(Long id) {
        Optional<PayMethod> optionalPayMethod = this.pay_methodRepository.findById(id);
        optionalPayMethod.ifPresent(
            PayMethodFound -> {
                this.pay_methodRepository.delete(PayMethodFound);
            }
        );
        return optionalPayMethod;
    }
 
    public List<PayMethod> findAll() {
        return (List<PayMethod>) this.pay_methodRepository.findAll();
    }

    public Optional<PayMethod> findById(Long id) {
        return this.pay_methodRepository.findById(id);
    }

    public PayMethod save(PayMethod PayMethod) {
        return this.pay_methodRepository.save(PayMethod);
    }

    public Optional<PayMethod> update(Long id, PayMethod pay_method) {
        Optional<PayMethod> optionalPayMethod = this.pay_methodRepository.findById(id);
        if (optionalPayMethod.isPresent()) {
            PayMethod pay_methodItem = optionalPayMethod.orElseThrow();
            //SETS
            
            return Optional.of(this.pay_methodRepository.save(pay_methodItem));
        }
        return optionalPayMethod;
    }
}
