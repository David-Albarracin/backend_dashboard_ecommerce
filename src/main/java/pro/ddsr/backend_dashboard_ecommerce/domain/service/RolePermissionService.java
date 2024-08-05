
package pro.ddsr.backend_dashboard_ecommerce.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backend_dashboard_ecommerce.domain.repository.RolePermissionRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.RolePermission;

@Service
public class RolePermissionService {
    // Define service methods here
    @Autowired
    RolePermissionRepository role_permissionRepository;
    
    @Transactional
    public Optional<RolePermission> delete(Long id) {
        Optional<RolePermission> optionalRolePermission = this.role_permissionRepository.findById(id);
        optionalRolePermission.ifPresent(
            RolePermissionFound -> {
                this.role_permissionRepository.delete(RolePermissionFound);
            }
        );
        return optionalRolePermission;
    }
 
    public List<RolePermission> findAll() {
        return (List<RolePermission>) this.role_permissionRepository.findAll();
    }

    public Optional<RolePermission> findById(Long id) {
        return this.role_permissionRepository.findById(id);
    }

    public RolePermission save(RolePermission RolePermission) {
        return this.role_permissionRepository.save(RolePermission);
    }

    public Optional<RolePermission> update(Long id, RolePermission role_permission) {
        Optional<RolePermission> optionalRolePermission = this.role_permissionRepository.findById(id);
        if (optionalRolePermission.isPresent()) {
            RolePermission role_permissionItem = optionalRolePermission.orElseThrow();
            //SETS
            
            return Optional.of(this.role_permissionRepository.save(role_permissionItem));
        }
        return optionalRolePermission;
    }
}
