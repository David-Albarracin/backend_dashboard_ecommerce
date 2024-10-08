
package pro.ddsr.backend_dashboard_ecommerce.transaction.web;

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
import pro.ddsr.backend_dashboard_ecommerce.transaction.domain.dto.TransactionDto;
import pro.ddsr.backend_dashboard_ecommerce.transaction.domain.service.TransactionService;
import pro.ddsr.backend_dashboard_ecommerce.transaction.persistence.Transaction;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public List<Transaction> listTransaction(){
        return this.transactionService.findAll();
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Transaction> view(@PathVariable Long id){
        Optional<Transaction> optionalTransaction  = transactionService.findById(id);
        if (optionalTransaction.isPresent()){
            return ResponseEntity.ok(optionalTransaction.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    // CU10
    @GetMapping("/bycustomer/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Transaction>> viewByCustomer(@PathVariable Long id){
        List<Transaction> listTransaction  = transactionService.findByCustomer(id);
        if (listTransaction.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listTransaction);
    }

    // CU14
    @GetMapping("/bypaymethod/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Transaction>> viewByPayMethod(@PathVariable Long id){
        List<Transaction> listTransaction  = transactionService.findByPayMethod(id);
        if (listTransaction.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listTransaction);
    }

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody TransactionDto transaction, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.save(transaction));
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Transaction> update(@PathVariable Long id, @Valid @RequestBody TransactionDto transaction){
        Optional<Transaction> transactionOptional = this.transactionService.update(id, transaction);
        if (transactionOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(transactionOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Transaction> delete(@PathVariable Long id){
        //Transaction transaction = new Transaction();
        //transaction.setId(id);
        Optional<Transaction> optionalTransaction = this.transactionService.delete(id);
        if (optionalTransaction.isPresent()){
            return ResponseEntity.ok(optionalTransaction.orElseThrow());
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
