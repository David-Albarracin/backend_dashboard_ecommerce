
package pro.ddsr.backend_dashboard_ecommerce.region.web;

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
import pro.ddsr.backend_dashboard_ecommerce.region.domain.service.RegionService;
import pro.ddsr.backend_dashboard_ecommerce.region.persistence.Region;

@RestController
@RequestMapping("/regions")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public List<Region> listRegion(){
        return this.regionService.findAll();
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Region> view(@PathVariable Long id){
        Optional<Region> optionalRegion  = regionService.findById(id);
        if (optionalRegion.isPresent()){
            return ResponseEntity.ok(optionalRegion.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody Region region, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(regionService.save(region));
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Region> update(@PathVariable Long id, @Valid @RequestBody Region region){
        Optional<Region> regionOptional = this.regionService.update(id, region);
        if (regionOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(regionOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Region> delete(@PathVariable Long id){
        //Region region = new Region();
        //region.setId(id);
        Optional<Region> optionalRegion = this.regionService.delete(id);
        if (optionalRegion.isPresent()){
            return ResponseEntity.ok(optionalRegion.orElseThrow());
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
