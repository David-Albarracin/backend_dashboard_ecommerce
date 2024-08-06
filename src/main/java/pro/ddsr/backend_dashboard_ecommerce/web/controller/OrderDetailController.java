
package pro.ddsr.backend_dashboard_ecommerce.web.controller;

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

import pro.ddsr.backend_dashboard_ecommerce.domain.service.OrderDetailService;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.OrderDetail;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/order_details")
public class OrderDetailController {

    @Autowired
    private OrderDetailService order_detailService;

    @GetMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public List<OrderDetail> listOrderDetail(){
        return this.order_detailService.findAll();
    }

    @GetMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderDetail> view(@PathVariable Long id){
        Optional<OrderDetail> optionalOrderDetail  = order_detailService.findById(id);
        if (optionalOrderDetail.isPresent()){
            return ResponseEntity.ok(optionalOrderDetail.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody OrderDetail order_detail, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(order_detailService.save(order_detail));
    }

    @PutMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderDetail> update(@PathVariable Long id, @Valid @RequestBody OrderDetail order_detail){
        Optional<OrderDetail> order_detailOptional = this.order_detailService.update(id, order_detail);
        if (order_detailOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(order_detailOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderDetail> delete(@PathVariable Long id){
        //OrderDetail order_detail = new OrderDetail();
        //order_detail.setId(id);
        Optional<OrderDetail> optionalOrderDetail = this.order_detailService.delete(id);
        if (optionalOrderDetail.isPresent()){
            return ResponseEntity.ok(optionalOrderDetail.orElseThrow());
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
