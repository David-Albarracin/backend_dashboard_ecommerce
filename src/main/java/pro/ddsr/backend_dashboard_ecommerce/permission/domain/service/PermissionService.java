
package pro.ddsr.backend_dashboard_ecommerce.permission.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import pro.ddsr.backend_dashboard_ecommerce.permission.domain.repository.PermissionRepository;
import pro.ddsr.backend_dashboard_ecommerce.permission.persistence.Permission;

@Service
public class PermissionService {
    // Define service methods here
    @Autowired
    PermissionRepository permissionRepository;
    
    @Transactional
    public Optional<Permission> delete(Long id) {
        Optional<Permission> optionalPermission = this.permissionRepository.findById(id);
        optionalPermission.ifPresent(
            PermissionFound -> {
                this.permissionRepository.delete(PermissionFound);
            }
        );
        return optionalPermission;
    }
 
    public List<Permission> findAll() {
        return (List<Permission>) this.permissionRepository.findAll();
    }

    public Optional<Permission> findById(Long id) {
        return this.permissionRepository.findById(id);
    }

    public Permission save(Permission Permission) {
        return this.permissionRepository.save(Permission);
    }

    public Optional<Permission> update(Long id, Permission permission) {
        Optional<Permission> optionalPermission = this.permissionRepository.findById(id);
        if (optionalPermission.isPresent()) {
            Permission permissionItem = optionalPermission.orElseThrow();
            //SETS
            
            return Optional.of(this.permissionRepository.save(permissionItem));
        }
        return optionalPermission;
    }
}
