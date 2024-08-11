
package pro.ddsr.backend_dashboard_ecommerce.transaction.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pro.ddsr.backend_dashboard_ecommerce.transaction.persistence.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Define repository methods here
    // CU4 (CRUD)

    // CU10
    @Query("SELECT t FROM Transaction t INNER JOIN t.order t0 INNER JOIN t0.customer t0c WHERE t0c.customerId =:idCustomer")
    List<Transaction> findByCustomer(Long idCustomer);

    // CU14
    @Query("SELECT t FROM Transaction t INNER JOIN t.payMethod tp WHERE tp.id =:idPayMethod")
    List<Transaction> findByPayMethod(Long idPayMethod);
}
