
package pro.ddsr.backend_dashboard_ecommerce.orderStatus.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import pro.ddsr.backend_dashboard_ecommerce.orderStatus.domain.service.OrderStatusService;
import pro.ddsr.backend_dashboard_ecommerce.orderStatus.persistence.OrderStatus;

@RestController
@RequestMapping("/order_status")
public class OrderStatusController {

    @Autowired
    private OrderStatusService order_statusService;

    @GetMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public List<OrderStatus> listOrderStatus(){
        return this.order_statusService.findAll();
    }

    @GetMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderStatus> view(@PathVariable Long id){
        Optional<OrderStatus> optionalOrderStatus  = order_statusService.findById(id);
        if (optionalOrderStatus.isPresent()){
            return ResponseEntity.ok(optionalOrderStatus.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody OrderStatus order_status, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(order_statusService.save(order_status));
    }

    @PutMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderStatus> update(@PathVariable Long id, @Valid @RequestBody OrderStatus order_status){
        Optional<OrderStatus> order_statusOptional = this.order_statusService.update(id, order_status);
        if (order_statusOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(order_statusOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderStatus> delete(@PathVariable Long id){
        //OrderStatus order_status = new OrderStatus();
        //order_status.setId(id);
        Optional<OrderStatus> optionalOrderStatus = this.order_statusService.delete(id);
        if (optionalOrderStatus.isPresent()){
            return ResponseEntity.ok(optionalOrderStatus.orElseThrow());
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
