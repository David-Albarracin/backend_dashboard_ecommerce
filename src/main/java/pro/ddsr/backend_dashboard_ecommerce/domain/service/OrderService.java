
package pro.ddsr.backend_dashboard_ecommerce.domain.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    // se inyecta el repositorio de customer
    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

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

    public Order save(Order Order) {
        return this.orderRepository.save(Order);
    }

    public Optional<Order> update(Long id, Order order) {
        Optional<Order> optionalOrder = this.orderRepository.findById(id);
        if (optionalOrder.isPresent()) {

            Order orderItem = optionalOrder.orElseThrow();
            
            orderItem.setCommentary( order.getCommentary());
            orderItem.setCustomer( order.getCustomer() );

            if ( order.getDeliverDate() != null){
                orderItem.setDeliverDate( order.getDeliverDate() );
            }

            orderItem.setExpectedDate( order.getExpectedDate());
            orderItem.setOrderType( order.getOrderType());
            orderItem.setOrderDate( order.getOrderDate());
            orderItem.setStatus( order.getStatus());
            //orderItem.setOrderdetails( order.getOrderdetails());
            
            return Optional.of(this.orderRepository.save(orderItem));
        }
        return optionalOrder;
    }

    /*
     * Parsea un dto a una Orden (REALIZA VALIDACIONES PERSONALIZADAS)
    */
    public Order NewOrder(OrderDto orderDto, BindingResult result){
        
        // busca el estado de la orden
        Optional<OrderStatus> foundOrderStatus = this.orderStatusRepository.findById( orderDto.getOrderStatusId());
        Optional<Customer> optionalCustomer = this.customerRepository.findById(orderDto.getCustomerId());

        if (!foundOrderStatus.isPresent()){
            return null;
        }
        if (!optionalCustomer.isPresent()){
            return null;

        }

        // construye la orden
        OrderStatus newOrderStatus = foundOrderStatus.get();
        Customer newCustomer = optionalCustomer.get();

        Order order = orderDto.toOrder(newCustomer, newOrderStatus);
        List<OrderDetail> orderDetails = new ArrayList<>();

        // pasar dto  a orden :(, por favor no poner muchos productos .-.
        for (OrderDetailDto detail : orderDto.getOrderdetails()){

            Product product = this.productRepository.findById(detail.getProductId()).get();
            orderDetails.add(detail.toOrderDetail(product, order));
        }

        // poner lista de detalles de producto en orden 
        //order.setOrderdetails(orderDetails);

        return order;

    }

    

}
