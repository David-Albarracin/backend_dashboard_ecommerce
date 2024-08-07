
package pro.ddsr.backend_dashboard_ecommerce.domain.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backend_dashboard_ecommerce.domain.repository.OrderRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Order;

@Service
public class OrderService {
    // Define service methods here
    @Autowired
    OrderRepository orderRepository;
    
    @Transactional
    public Optional<Order> delete(Long id) {
        Optional<Order> optionalOrder = this.orderRepository.findById(id);
        optionalOrder.ifPresent(
            OrderFound -> {
                this.orderRepository.delete(OrderFound);
            }
        );
        return optionalOrder;
    }
 
    public List<Order> findAll() {
        return (List<Order>) this.orderRepository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return this.orderRepository.findById(id);
    }

    public List<Order> findByStatus(String nameStatus) {
        return this.orderRepository.findByStatus(nameStatus);
    }

    public List<Order> getOrdersInDateRange(LocalDate startDate, LocalDate endDate) {
        return orderRepository.findOrdersInDateRange(startDate, endDate);
    }

    public Order save(Order Order) {
        return this.orderRepository.save(Order);
    }

    public Optional<Order> update(Long id, Order order) {
        Optional<Order> optionalOrder = this.orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order orderItem = optionalOrder.orElseThrow();
            //SETS
            
            return Optional.of(this.orderRepository.save(orderItem));
        }
        return optionalOrder;
    }
}
