
package pro.ddsr.backend_dashboard_ecommerce.web.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import pro.ddsr.backend_dashboard_ecommerce.domain.dto.OrderDto.OrderDto;
import pro.ddsr.backend_dashboard_ecommerce.domain.dto.OrderDto.OrderDtoJavaOutput;
import pro.ddsr.backend_dashboard_ecommerce.domain.service.OrderService;
import pro.ddsr.backend_dashboard_ecommerce.persistence.crud.OrderProjection;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Order;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public List<OrderProjection> listOrder(){
        List<OrderProjection> orders = orderService.findAllProjections();
        orders.forEach(order -> System.out.println(order)); // Imprime cada pedido en la consola
        return orders;
    }

    @GetMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderDtoJavaOutput> view(@PathVariable Long id){
        Optional<OrderDtoJavaOutput> optionalOrder  = orderService.findById(id);
        if (optionalOrder.isPresent()){
            return ResponseEntity.ok(optionalOrder.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // CU9
    @GetMapping("/bystatus/{statusId}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderProjection>> viewByStatus(@PathVariable Long statusId){
        List<OrderProjection> listOrder  = orderService.findByStatus(statusId);
        if (listOrder.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listOrder);
    }

    // CU13
    @GetMapping("/bydaterange")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Order>> viewByDateRange(
        @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
        @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        List<Order> listOrder  = orderService.getOrdersInDateRange(startDate, endDate);
        if (listOrder.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listOrder);
    }

    @PostMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody OrderDto orderDto, BindingResult result){

        // validaciones de validator de spring
        if (result.hasFieldErrors()) {
            return validation(result);
        }

        // validaciones personalizadas
        Order order = this.orderService.NewOrder(orderDto, result);
        if (order == null) {
            return ResponseEntity.badRequest().body("Errores de validacion (id de cliente / id de estado de orden)");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(order));
    }

    @PutMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody OrderDto orderDto, BindingResult result){
        // validaciones de validator de spring
        if (result.hasFieldErrors()) {
            return ResponseEntity.badRequest().body(result);
        }

        // validaciones personalizadas
        Order order = this.orderService.NewOrder(orderDto, result);
        if (order == null) {
            return ResponseEntity.badRequest().body("Errores de validacion (id de cliente / id de estado de orden)");
        }

        Optional<Order> orderOptional = this.orderService.update(id, order);
        // valida que exista la orden
        if (orderOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(orderOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Order> delete(@PathVariable Long id){
        //Order order = new Order();
        //order.setId(id);
        Optional<Order> optionalOrder = this.orderService.delete(id);
        if (optionalOrder.isPresent()){
            return ResponseEntity.ok(optionalOrder.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}