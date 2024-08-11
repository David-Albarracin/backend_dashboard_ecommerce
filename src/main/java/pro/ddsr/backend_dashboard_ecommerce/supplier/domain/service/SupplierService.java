
package pro.ddsr.backend_dashboard_ecommerce.supplier.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import pro.ddsr.backend_dashboard_ecommerce.supplier.domain.repository.SupplierRepository;
import pro.ddsr.backend_dashboard_ecommerce.supplier.persistence.Supplier;

@Service
public class SupplierService {
    // Define service methods here
    @Autowired
    SupplierRepository supplierRepository;
    
    @Transactional
    public Optional<Supplier> delete(Long id) {
        Optional<Supplier> optionalSupplier = this.supplierRepository.findById(id);
        optionalSupplier.ifPresent(
            SupplierFound -> {
                this.supplierRepository.delete(SupplierFound);
            }
        );
        return optionalSupplier;
    }
 
    public List<Supplier> findAll() {
        return (List<Supplier>) this.supplierRepository.findAll();
    }

    public Optional<Supplier> findById(Long id) {
        return this.supplierRepository.findById(id);
    }

    public Supplier save(Supplier Supplier) {
        return this.supplierRepository.save(Supplier);
    }

    public Optional<Supplier> update(Long id, Supplier supplier) {
        Optional<Supplier> optionalSupplier = this.supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            Supplier supplierItem = optionalSupplier.orElseThrow();
            //SETS
            
            return Optional.of(this.supplierRepository.save(supplierItem));
        }
        return optionalSupplier;
    }
}
