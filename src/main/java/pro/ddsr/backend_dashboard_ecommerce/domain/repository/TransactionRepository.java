
package pro.ddsr.backend_dashboard_ecommerce.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Define repository methods here
    // CU4 (CRUD)

    // CU10
    @Query("SELECT t FROM Transaction t INNER JOIN t.order t0 INNER JOIN t0.customer t0c WHERE t0c.customerId =?1")
    List<Transaction> findByCustomer(Long idCustomer);

    // CU14
    @Query("SELECT t FROM Transaction t INNER JOIN t.payMethod tp WHERE tp.name =?1")
    List<Transaction> findByPayMethod(String payMethodName);
}
