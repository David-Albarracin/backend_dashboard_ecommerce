
package pro.ddsr.backend_dashboard_ecommerce.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.ddsr.backend_dashboard_ecommerce.domain.service.ProductGamaService;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.ProductGama;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product_gama")
public class ProductGamaController {

    @Autowired
    private ProductGamaService product_gamaService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<ProductGama> listProductGama(){
        return this.product_gamaService.findAll();
    }

    @GetMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<ProductGama> view(@PathVariable Long id){
        Optional<ProductGama> optionalProductGama  = product_gamaService.findById(id);
        if (optionalProductGama.isPresent()){
            return ResponseEntity.ok(optionalProductGama.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody ProductGama product_gama, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(product_gamaService.save(product_gama));
    }

    @PutMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<ProductGama> update(@PathVariable Long id, @Valid @RequestBody ProductGama product_gama){
        Optional<ProductGama> product_gamaOptional = this.product_gamaService.update(id, product_gama);
        if (product_gamaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(product_gamaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<ProductGama> delete(@PathVariable Long id){
        //ProductGama product_gama = new ProductGama();
        //product_gama.setId(id);
        Optional<ProductGama> optionalProductGama = this.product_gamaService.delete(id);
        if (optionalProductGama.isPresent()){
            return ResponseEntity.ok(optionalProductGama.orElseThrow());
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
