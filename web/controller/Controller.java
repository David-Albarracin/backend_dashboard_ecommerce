
package backend_dashboard_ecommerce.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend_dashboard_ecommerce.domain.service.Service;
import backend_dashboard_ecommerce.persistence.entity.;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    private Service Service;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<> list(){
        return this.Service.findAll();
    }

    @GetMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<> view(@PathVariable Long id){
        Optional<> optional  = Service.findById(id);
        if (optional.isPresent()){
            return ResponseEntity.ok(optional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody  , BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(Service.save());
    }

    @PutMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<> update(@PathVariable Long id, @Valid @RequestBody  ){
        Optional<> Optional = this.Service.update(id, );
        if (Optional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(Optional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<> delete(@PathVariable Long id){
        //  = new ();
        //.setId(id);
        Optional<> optional = this.Service.delete(id);
        if (optional.isPresent()){
            return ResponseEntity.ok(optional.orElseThrow());
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
