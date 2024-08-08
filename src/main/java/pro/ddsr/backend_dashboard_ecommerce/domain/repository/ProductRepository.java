
package pro.ddsr.backend_dashboard_ecommerce.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Define repository methods here
    // CU1, CU22 (CRUD)
    // CU23 (create)
    // CU24 (update)
    // CU25 (delete)

    // CU7
    @Query("SELECT p FROM Product p INNER JOIN p.productGama pg WHERE pg.id =:idGama")
    List<Product> findByGama(Long idGama);

    // CU12
    @Query("SELECT p FROM Product p WHERE p.stock <?1")
    List<Product> findByStock(Byte stock);
}