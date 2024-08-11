
package pro.ddsr.backend_dashboard_ecommerce.orderDetail.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import pro.ddsr.backend_dashboard_ecommerce.orderDetail.domain.repository.OrderDetailRepository;
import pro.ddsr.backend_dashboard_ecommerce.orderDetail.persistence.OrderDetail;

@Service
public class OrderDetailService {
    // Define service methods here
    @Autowired
    OrderDetailRepository order_detailRepository;
    
    @Transactional
    public Optional<OrderDetail> delete(Long id) {
        Optional<OrderDetail> optionalOrderDetail = this.order_detailRepository.findById(id);
        optionalOrderDetail.ifPresent(
            OrderDetailFound -> {
                this.order_detailRepository.delete(OrderDetailFound);
            }
        );
        return optionalOrderDetail;
    }
 
    public List<OrderDetail> findAll() {
        return (List<OrderDetail>) this.order_detailRepository.findAll();
    }

    public Optional<OrderDetail> findById(Long id) {
        return this.order_detailRepository.findById(id);
    }

    public OrderDetail save(OrderDetail OrderDetail) {
        return this.order_detailRepository.save(OrderDetail);
    }

    public Optional<OrderDetail> update(Long id, OrderDetail order_detail) {
        Optional<OrderDetail> optionalOrderDetail = this.order_detailRepository.findById(id);
        if (optionalOrderDetail.isPresent()) {
            OrderDetail order_detailItem = optionalOrderDetail.orElseThrow();
            //SETS
            
            return Optional.of(this.order_detailRepository.save(order_detailItem));
        }
        return optionalOrderDetail;
    }
}
