
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

import pro.ddsr.backend_dashboard_ecommerce.domain.service.CityService;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.City;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<City> listCity(){
        return this.cityService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<City> view(@PathVariable Long id){
        Optional<City> optionalCity  = cityService.findById(id);
        if (optionalCity.isPresent()){
            return ResponseEntity.ok(optionalCity.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody City city, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cityService.save(city));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<City> update(@PathVariable Long id, @Valid @RequestBody City city){
        Optional<City> cityOptional = this.cityService.update(id, city);
        if (cityOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(cityOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<City> delete(@PathVariable Long id){
        //City city = new City();
        //city.setId(id);
        Optional<City> optionalCity = this.cityService.delete(id);
        if (optionalCity.isPresent()){
            return ResponseEntity.ok(optionalCity.orElseThrow());
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
