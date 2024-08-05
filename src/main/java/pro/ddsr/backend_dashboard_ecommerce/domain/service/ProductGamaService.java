
package pro.ddsr.backend_dashboard_ecommerce.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backend_dashboard_ecommerce.domain.repository.ProductGamaRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.ProductGama;

@Service
public class ProductGamaService {
    // Define service methods here
    @Autowired
    ProductGamaRepository product_gamaRepository;
    
    @Transactional
    public Optional<ProductGama> delete(Long id) {
        Optional<ProductGama> optionalProductGama = this.product_gamaRepository.findById(id);
        optionalProductGama.ifPresent(
            ProductGamaFound -> {
                this.product_gamaRepository.delete(ProductGamaFound);
            }
        );
        return optionalProductGama;
    }
 
    public List<ProductGama> findAll() {
        return (List<ProductGama>) this.product_gamaRepository.findAll();
    }

    public Optional<ProductGama> findById(Long id) {
        return this.product_gamaRepository.findById(id);
    }

    public ProductGama save(ProductGama ProductGama) {
        return this.product_gamaRepository.save(ProductGama);
    }

    public Optional<ProductGama> update(Long id, ProductGama product_gama) {
        Optional<ProductGama> optionalProductGama = this.product_gamaRepository.findById(id);
        if (optionalProductGama.isPresent()) {
            ProductGama product_gamaItem = optionalProductGama.orElseThrow();
            //SETS
            
            return Optional.of(this.product_gamaRepository.save(product_gamaItem));
        }
        return optionalProductGama;
    }
}
