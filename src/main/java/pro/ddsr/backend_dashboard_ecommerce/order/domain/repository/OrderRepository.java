
package pro.ddsr.backend_dashboard_ecommerce.order.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pro.ddsr.backend_dashboard_ecommerce.employee.domain.dto.EmployeesWithOrdersProjection;
import pro.ddsr.backend_dashboard_ecommerce.order.domain.dto.OrderProjection;
import pro.ddsr.backend_dashboard_ecommerce.order.persistence.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Define repository methods here
    // CU3, CU27 (CRUD)

    // CU9
    @Query("SELECT o FROM Order o INNER JOIN o.status os WHERE os.id =:statusId")
    List<OrderProjection> findByStatus(@Param("statusId") Long statusId);

    @Query("SELECT o FROM Order o")
    List<OrderProjection> findAllOrderSummaries();
    // CU13
    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate")
    List<Order> findOrdersInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

     //CU16
     @Query("SELECT e FROM Employee e INNER JOIN e.customers ec INNER JOIN ec.orders eco")
     List<EmployeesWithOrdersProjection> findAllEmployeesWithOrders();
}
//     @Query("SELECT o FROM Order o " +
//     "INNER JOIN o.customer c " +
//     "INNER JOIN c.employee e")
//     List<EmployeesWithOrdersDto> findOrdersWithCustomersAndEmployees();
// }
