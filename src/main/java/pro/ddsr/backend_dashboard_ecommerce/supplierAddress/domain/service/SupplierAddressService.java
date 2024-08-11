
package pro.ddsr.backend_dashboard_ecommerce.supplierAddress.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import pro.ddsr.backend_dashboard_ecommerce.supplierAddress.domain.repository.SupplierAddressRepository;
import pro.ddsr.backend_dashboard_ecommerce.supplierAddress.persistence.SupplierAddress;

@Service
public class SupplierAddressService {
    // Define service methods here
    @Autowired
    SupplierAddressRepository supplier_addressRepository;
    
    @Transactional
    public Optional<SupplierAddress> delete(Long id) {
        Optional<SupplierAddress> optionalSupplierAddress = this.supplier_addressRepository.findById(id);
        optionalSupplierAddress.ifPresent(
            SupplierAddressFound -> {
                this.supplier_addressRepository.delete(SupplierAddressFound);
            }
        );
        return optionalSupplierAddress;
    }
 
    public List<SupplierAddress> findAll() {
        return (List<SupplierAddress>) this.supplier_addressRepository.findAll();
    }

    public Optional<SupplierAddress> findById(Long id) {
        return this.supplier_addressRepository.findById(id);
    }

    public SupplierAddress save(SupplierAddress SupplierAddress) {
        return this.supplier_addressRepository.save(SupplierAddress);
    }

    public Optional<SupplierAddress> update(Long id, SupplierAddress supplier_address) {
        Optional<SupplierAddress> optionalSupplierAddress = this.supplier_addressRepository.findById(id);
        if (optionalSupplierAddress.isPresent()) {
            SupplierAddress supplier_addressItem = optionalSupplierAddress.orElseThrow();
            //SETS
            
            return Optional.of(this.supplier_addressRepository.save(supplier_addressItem));
        }
        return optionalSupplierAddress;
    }
}
