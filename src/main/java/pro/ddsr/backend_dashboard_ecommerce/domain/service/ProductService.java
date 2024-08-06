
package pro.ddsr.backend_dashboard_ecommerce.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backend_dashboard_ecommerce.domain.repository.ProductRepository;
import pro.ddsr.backend_dashboard_ecommerce.domain.dto.ProductDto;
import pro.ddsr.backend_dashboard_ecommerce.domain.repository.ProductGamaRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Product;

@Service
public class ProductService {
    // Define service methods here
    @Autowired
    ProductRepository productRepository;

     @Autowired
    private ProductGamaRepository productGamaRepository;
    
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

    public List<Product> findByGama(String gamaName) {
        return this.productRepository.findByGama(gamaName);
    }

    public List<Product> findByStock(Byte stock) {
        return this.productRepository.findByStock(stock);
    }

    public Product save(Product product) {
        // ProductGama productGama = productGamaRepository.findById(product.getProductGama().getProductGamaId())
        //         .orElseThrow(() -> new RuntimeException("ProductGama not found"));

        // product.setProductGama(productGama);

        return this.productRepository.save(product);
    }

    public Optional<Product> update(Long id, ProductDto product) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product productItem = optionalProduct.orElseThrow();
            productItem.setProductId(id);
            productItem.setCode(product.getCode());
            productItem.setName(product.getName());
            productItem.setDescription(product.getDescription());
            productItem.setStock(product.getStock());
            productItem.setPriceSale(product.getPriceSale());
            productItem.setPriceBuy(product.getPriceBuy());
            productItem.setProductGama(productGamaRepository.findById(product.getProductGama()).get());
                    
            return Optional.of(this.productRepository.save(productItem));
        }
        return optionalProduct;
    }
}
