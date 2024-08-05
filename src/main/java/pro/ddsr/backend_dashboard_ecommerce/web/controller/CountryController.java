
package pro.ddsr.backend_dashboard_ecommerce.web.controller;

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

import pro.ddsr.backend_dashboard_ecommerce.domain.service.CountryService;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Country;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Country> listCountry(){
        return this.countryService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Country> view(@PathVariable Long id){
        Optional<Country> optionalCountry  = countryService.findById(id);
        if (optionalCountry.isPresent()){
            return ResponseEntity.ok(optionalCountry.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Country country, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(countryService.save(country));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Country> update(@PathVariable Long id, @Valid @RequestBody Country country){
        Optional<Country> countryOptional = this.countryService.update(id, country);
        if (countryOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(countryOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Country> delete(@PathVariable Long id){
        //Country country = new Country();
        //country.setId(id);
        Optional<Country> optionalCountry = this.countryService.delete(id);
        if (optionalCountry.isPresent()){
            return ResponseEntity.ok(optionalCountry.orElseThrow());
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
