
package pro.ddsr.backend_dashboard_ecommerce.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backend_dashboard_ecommerce.domain.repository.ProductRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Product;

@Service
public class ProductService {
    // Define service methods here
    @Autowired
    ProductRepository productRepository;
    
    @Transactional
    public Optional<Product> delete(Long id) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);
        optionalProduct.ifPresent(
            ProductFound -> {
                this.productRepository.delete(ProductFound);
            }
        );
        return optionalProduct;
    }
 
    public List<Product> findAll() {
        return (List<Product>) this.productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    public Product save(Product Product) {
        return this.productRepository.save(Product);
    }

    public Optional<Product> update(Long id, Product product) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product productItem = optionalProduct.orElseThrow();
            //SETS
            
            return Optional.of(this.productRepository.save(productItem));
        }
        return optionalProduct;
    }
}
