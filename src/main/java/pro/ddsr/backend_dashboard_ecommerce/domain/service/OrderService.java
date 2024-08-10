
package pro.ddsr.backend_dashboard_ecommerce.domain.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import jakarta.transaction.Transactional;
import pro.ddsr.backend_dashboard_ecommerce.domain.dto.orderDto.OrderDetailDto;
import pro.ddsr.backend_dashboard_ecommerce.domain.dto.orderDto.OrderDto;
import pro.ddsr.backend_dashboard_ecommerce.domain.dto.orderDto.OrderDtoOutput;
import pro.ddsr.backend_dashboard_ecommerce.domain.repository.CustomerRepository;
import pro.ddsr.backend_dashboard_ecommerce.domain.repository.OrderDetailRepository;
import pro.ddsr.backend_dashboard_ecommerce.domain.repository.OrderRepository;
import pro.ddsr.backend_dashboard_ecommerce.domain.repository.OrderStatusRepository;
import pro.ddsr.backend_dashboard_ecommerce.domain.repository.ProductRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.crud.OrderDetailProjection;
import pro.ddsr.backend_dashboard_ecommerce.persistence.crud.OrderProjection;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Customer;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Order;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.OrderDetail;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.OrderStatus;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Product;

@Service
public class OrderService {
    // Define service methods here
    @Autowired
    OrderRepository orderRepository;


    @Autowired
    OrderDetailRepository orderDetailRepository;

    
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

    public List<OrderProjection> findAllProjections() {
        
        return this.orderRepository.findAllOrderSummaries();
    }

    

    public Optional<OrderDtoOutput> findById(Long id) {
        
        Optional<Order> optionalOrder = this.orderRepository.findById(id);

        if (optionalOrder.isPresent()){
            List<OrderDetailProjection> summarizeDetails = this.orderDetailRepository.findOrderDetailsByOrderId(id);
            OrderDtoOutput foundOrder = OrderDtoOutput.toDto(summarizeDetails, optionalOrder.get());
            return Optional.of(foundOrder);

        }
        return Optional.empty();
    }

    public List<OrderProjection> findByStatus(Long statusId) {
        return this.orderRepository.findByStatus(statusId);
    }

    public List<Order> getOrdersInDateRange(LocalDate startDate, LocalDate endDate) {
        return orderRepository.findOrdersInDateRange(startDate, endDate);
    }



    public Optional<Order> update(Long id, OrderDto order) {
        Optional<Order> optionalOrder = this.orderRepository.findById(id);
        if (optionalOrder.isPresent()) {

           order.setOrderId(id); // solo por si acaso xd
           Order updateOrder = this.NewOrder(order);


        }
        return optionalOrder;
    }

    /*
     * Parsea un dto a una Orden  y sus detalles, y lo guarda
    */
    public Order NewOrder(OrderDto orderDto){
        
       // convertir el dto de orden  y guardar
       Order newOrder = this.orderRepository.save(OrderDto.toOrder(orderDto));


       // convertir los detalles de orden 
    Set<OrderDetail> orderDetails = orderDto.getOrderdetails().stream()
        .map( dto -> {
            dto.setOrderId( newOrder.getOrderId());
            return OrderDetailDto.toOrderDetail(dto);
        })
        .collect( Collectors.toSet());
    
        this.orderDetailRepository.saveAll(orderDetails);
        newOrder.setOrderDetails(orderDetails);

        return newOrder;

    }

    

}
