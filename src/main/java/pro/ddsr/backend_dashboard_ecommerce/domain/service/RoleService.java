
package pro.ddsr.backend_dashboard_ecommerce.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backend_dashboard_ecommerce.domain.repository.RoleRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Role;

@Service
public class RoleService {
    // Define service methods here
    @Autowired
    RoleRepository roleRepository;
    
    @Transactional
    public Optional<Role> delete(Long id) {
        Optional<Role> optionalRole = this.roleRepository.findById(id);
        optionalRole.ifPresent(
            RoleFound -> {
                this.roleRepository.delete(RoleFound);
            }
        );
        return optionalRole;
    }
 
    public List<Role> findAll() {
        return (List<Role>) this.roleRepository.findAll();
    }

    public Optional<Role> findById(Long id) {
        return this.roleRepository.findById(id);
    }

    public Role save(Role Role) {
        return this.roleRepository.save(Role);
    }

    public Optional<Role> update(Long id, Role role) {
        Optional<Role> optionalRole = this.roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            Role roleItem = optionalRole.orElseThrow();
            //SETS
            
            return Optional.of(this.roleRepository.save(roleItem));
        }
        return optionalRole;
    }
}
