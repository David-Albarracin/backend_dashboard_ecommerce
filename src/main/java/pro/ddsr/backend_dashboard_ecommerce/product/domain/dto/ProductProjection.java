package pro.ddsr.backend_dashboard_ecommerce.product.domain.dto;

public interface ProductProjection {
    Long getProductId();
    String getCode();
    String getName();
    ProductGamaProjection getProductGama();
    SupplierProjection getSupplier();

    public interface ProductGamaProjection{
        Long getProductGamaId();
        String getName();
    }

    public interface SupplierProjection {
        Long getSupplierId();
        String getName();
        
    }


}
