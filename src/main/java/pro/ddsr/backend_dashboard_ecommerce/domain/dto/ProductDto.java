
package pro.ddsr.backend_dashboard_ecommerce.domain.dto;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Product;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.ProductGama;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    // Define attributes here
    @Size(max = 45)
    private String code;

    @Size(max = 45)
    private String name;

    private String description;

    private Byte stock;

    private Integer priceSale;

    private Integer priceBuy;

    private Long productGama; 
    // Define constructor(s) here
    public Product toProduct(ProductGama productGama) {
        Product product = new Product();
        product.setCode(this.code);
        product.setName(this.name);
        product.setDescription(this.description);
        product.setStock(this.stock);
        product.setPriceSale(this.priceSale);
        product.setPriceBuy(this.priceBuy);
        product.setProductGama(productGama);
        return product;
    }
    // Define getter and setter methods here
}