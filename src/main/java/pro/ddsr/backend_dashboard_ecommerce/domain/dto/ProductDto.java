
package pro.ddsr.backend_dashboard_ecommerce.domain.dto;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

    // Define getter and setter methods here
}
