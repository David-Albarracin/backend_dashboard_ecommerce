
package pro.ddsr.backend_dashboard_ecommerce.product.domain.dto;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pro.ddsr.backend_dashboard_ecommerce.product.persistence.Product;
import pro.ddsr.backend_dashboard_ecommerce.productGama.persistence.ProductGama;
import pro.ddsr.backend_dashboard_ecommerce.supplier.persistence.Supplier;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    // Define attributes here
    private Long productId;

    @Size(max = 45)
    private String code;

    @Size(max = 45)
    private String name;

    private String description;

    private Byte stock;

    private Integer priceSale;

    private Integer priceBuy;

    private Long productGama; 

    private Long supplier;
    // Define constructor(s) here
    public Product toProduct(ProductGama productGama, Supplier supplier) {
        Product product = new Product();
        if (this.productId != null) {
            product.setProductId(this.productId);
        }
        product.setCode(this.code);
        product.setName(this.name);
        product.setDescription(this.description);
        product.setStock(this.stock);
        product.setPriceSale(this.priceSale);
        product.setPriceBuy(this.priceBuy);
        product.setProductGama(productGama);
        product.setSupplier(supplier);
        return product;
    }
    // Define getter and setter methods here
}