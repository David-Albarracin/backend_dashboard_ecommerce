
package pro.ddsr.backend_dashboard_ecommerce.supplierPhone.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import pro.ddsr.backend_dashboard_ecommerce.supplierPhone.domain.repository.SupplierPhoneRepository;
import pro.ddsr.backend_dashboard_ecommerce.supplierPhone.persistence.SupplierPhone;

@Service
public class SupplierPhoneService {
    // Define service methods here
    @Autowired
    SupplierPhoneRepository supplier_phoneRepository;
    
    @Transactional
    public Optional<SupplierPhone> delete(Long id) {
        Optional<SupplierPhone> optionalSupplierPhone = this.supplier_phoneRepository.findById(id);
        optionalSupplierPhone.ifPresent(
            SupplierPhoneFound -> {
                this.supplier_phoneRepository.delete(SupplierPhoneFound);
            }
        );
        return optionalSupplierPhone;
    }
 
    public List<SupplierPhone> findAll() {
        return (List<SupplierPhone>) this.supplier_phoneRepository.findAll();
    }

    public Optional<SupplierPhone> findById(Long id) {
        return this.supplier_phoneRepository.findById(id);
    }

    public SupplierPhone save(SupplierPhone SupplierPhone) {
        return this.supplier_phoneRepository.save(SupplierPhone);
    }

    public Optional<SupplierPhone> update(Long id, SupplierPhone supplier_phone) {
        Optional<SupplierPhone> optionalSupplierPhone = this.supplier_phoneRepository.findById(id);
        if (optionalSupplierPhone.isPresent()) {
            SupplierPhone supplier_phoneItem = optionalSupplierPhone.orElseThrow();
            //SETS
            
            return Optional.of(this.supplier_phoneRepository.save(supplier_phoneItem));
        }
        return optionalSupplierPhone;
    }
}
