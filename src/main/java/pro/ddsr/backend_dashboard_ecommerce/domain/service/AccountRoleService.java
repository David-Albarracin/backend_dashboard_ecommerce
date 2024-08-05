
package pro.ddsr.backend_dashboard_ecommerce.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backend_dashboard_ecommerce.domain.repository.AccountRoleRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.AccountRole;

@Service
public class AccountRoleService {
    // Define service methods here
    @Autowired
    AccountRoleRepository account_roleRepository;
    
    @Transactional
    public Optional<AccountRole> delete(Long id) {
        Optional<AccountRole> optionalAccountRole = this.account_roleRepository.findById(id);
        optionalAccountRole.ifPresent(
            AccountRoleFound -> {
                this.account_roleRepository.delete(AccountRoleFound);
            }
        );
        return optionalAccountRole;
    }
 
    public List<AccountRole> findAll() {
        return (List<AccountRole>) this.account_roleRepository.findAll();
    }

    public Optional<AccountRole> findById(Long id) {
        return this.account_roleRepository.findById(id);
    }

    public AccountRole save(AccountRole AccountRole) {
        return this.account_roleRepository.save(AccountRole);
    }

    public Optional<AccountRole> update(Long id, AccountRole account_role) {
        Optional<AccountRole> optionalAccountRole = this.account_roleRepository.findById(id);
        if (optionalAccountRole.isPresent()) {
            AccountRole account_roleItem = optionalAccountRole.orElseThrow();
            //SETS
            
            return Optional.of(this.account_roleRepository.save(account_roleItem));
        }
        return optionalAccountRole;
    }
}
