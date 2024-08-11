
package pro.ddsr.backend_dashboard_ecommerce.product.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import pro.ddsr.backend_dashboard_ecommerce.product.domain.dto.ProductDto;
import pro.ddsr.backend_dashboard_ecommerce.product.domain.repository.ProductRepository;
import pro.ddsr.backend_dashboard_ecommerce.product.persistence.Product;
import pro.ddsr.backend_dashboard_ecommerce.productGama.domain.repository.ProductGamaRepository;
import pro.ddsr.backend_dashboard_ecommerce.productGama.persistence.ProductGama;
import pro.ddsr.backend_dashboard_ecommerce.supplier.domain.repository.SupplierRepository;
import pro.ddsr.backend_dashboard_ecommerce.supplier.persistence.Supplier;

@Service
public class ProductService {
    // Define service methods here
    @Autowired
    ProductRepository productRepository;

    @Autowired
    private ProductGamaRepository productGamaRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Transactional
    public Optional<Product> delete(Long id) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);
        optionalProduct.ifPresent(
                ProductFound -> {
                    this.productRepository.delete(ProductFound);
                });
        return optionalProduct;
    }

    public List<Product> findAll() {
        return (List<Product>) this.productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    public List<Product> findByGama(Long id) {
        return this.productRepository.findByGama(id);
    }

    public List<Product> findByStock(Byte stock) {
        return this.productRepository.findByStock(stock);
    }

    public Product save(ProductDto product) {
        Product productItem = product.toProduct(productGamaRepository.findById(product.getProductGama()).get(),
                supplierRepository.findById(product.getSupplier()).get());
        return this.productRepository.save(productItem);
    }

    public Optional<Product> update(Long id, ProductDto productDto) {
        // Buscar el producto por id
        Optional<Product> optionalProduct = this.productRepository.findById(id);

        if (!optionalProduct.isPresent()) {
            return Optional.empty(); // Producto no encontrado
        }

        // Buscar ProductGama y Supplier
        ProductGama productGama = productGamaRepository.findById(productDto.getProductGama())
                .orElseThrow(() -> new IllegalArgumentException("ProductGama no encontrado"));
        Supplier supplier = supplierRepository.findById(productDto.getSupplier())
                .orElseThrow(() -> new IllegalArgumentException("Supplier no encontrado"));

        // Crear y actualizar el producto
        Product productItem = productDto.toProduct(productGama, supplier);
        productItem.setProductId(id); // Asegúrate de que el ID esté establecido para la actualización

        try {
            return Optional.of(this.productRepository.save(productItem));
        } catch (DataIntegrityViolationException e) {
            // Manejar conflicto de integridad de datos (clave única)
            return Optional.empty();
        }
    }

}