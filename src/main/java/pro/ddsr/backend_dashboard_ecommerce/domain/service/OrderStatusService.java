
package pro.ddsr.backend_dashboard_ecommerce.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backend_dashboard_ecommerce.domain.repository.OrderStatusRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.OrderStatus;

@Service
public class OrderStatusService {
    // Define service methods here
    @Autowired
    OrderStatusRepository order_statusRepository;
    
    @Transactional
    public Optional<OrderStatus> delete(Long id) {
        Optional<OrderStatus> optionalOrderStatus = this.order_statusRepository.findById(id);
        optionalOrderStatus.ifPresent(
            OrderStatusFound -> {
                this.order_statusRepository.delete(OrderStatusFound);
            }
        );
        return optionalOrderStatus;
    }
 
    public List<OrderStatus> findAll() {
        return (List<OrderStatus>) this.order_statusRepository.findAll();
    }

    public Optional<OrderStatus> findById(Long id) {
        return this.order_statusRepository.findById(id);
    }

    public OrderStatus save(OrderStatus OrderStatus) {
        return this.order_statusRepository.save(OrderStatus);
    }

    public Optional<OrderStatus> update(Long id, OrderStatus order_status) {
        Optional<OrderStatus> optionalOrderStatus = this.order_statusRepository.findById(id);
        if (optionalOrderStatus.isPresent()) {
            OrderStatus order_statusItem = optionalOrderStatus.orElseThrow();
            //SETS
            
            return Optional.of(this.order_statusRepository.save(order_statusItem));
        }
        return optionalOrderStatus;
    }
}
