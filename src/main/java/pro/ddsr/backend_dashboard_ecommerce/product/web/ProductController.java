
package pro.ddsr.backend_dashboard_ecommerce.product.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pro.ddsr.backend_dashboard_ecommerce.product.domain.dto.ProductDto;
import pro.ddsr.backend_dashboard_ecommerce.product.domain.service.ProductService;
import pro.ddsr.backend_dashboard_ecommerce.product.persistence.Product;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> listProduct(){
        return this.productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> view(@PathVariable Long id){
        Optional<Product> optionalProduct  = productService.findById(id);
        if (optionalProduct.isPresent()){
            return ResponseEntity.ok(optionalProduct.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // CU7
    @GetMapping("/bygama/{id}")
    public ResponseEntity<List<Product>> viewByGama(@PathVariable Long id){
        List<Product> listProduct  = productService.findByGama(id);
        if (listProduct.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listProduct);
    }

    // CU12
    @GetMapping("/byminstock")
    public ResponseEntity<List<Product>> viewByStock(@RequestParam Byte stock){
        List<Product> listProduct  = productService.findByStock(stock);
        if (listProduct.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listProduct);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ProductDto product, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody ProductDto product){
        Optional<Product> productOptional = this.productService.update(id, product);
        if (productOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable Long id){
        //Product product = new Product();
        //product.setId(id);
        Optional<Product> optionalProduct = this.productService.delete(id);
        if (optionalProduct.isPresent()){
            return ResponseEntity.ok(optionalProduct.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}