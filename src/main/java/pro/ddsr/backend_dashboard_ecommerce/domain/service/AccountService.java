
package pro.ddsr.backend_dashboard_ecommerce.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import pro.ddsr.backend_dashboard_ecommerce.domain.repository.AccountRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Account;

@Service
public class AccountService {
    // Define service methods here
    @Autowired
    AccountRepository accountRepository;
    
    @Transactional
    public Optional<Account> delete(Long id) {
        Optional<Account> optionalAccount = this.accountRepository.findById(id);
        optionalAccount.ifPresent(
            AccountFound -> {
                this.accountRepository.delete(AccountFound);
            }
        );
        return optionalAccount;
    }
 
    public List<Account> findAll() {
        return (List<Account>) this.accountRepository.findAll();
    }

    public Optional<Account> findById(Long id) {
        return this.accountRepository.findById(id);
    }

    public Account save(Account Account) {
        return this.accountRepository.save(Account);
    }

    public Optional<Account> update(Long id, Account account) {
        Optional<Account> optionalAccount = this.accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            Account accountItem = optionalAccount.orElseThrow();
            //SETS
            
            return Optional.of(this.accountRepository.save(accountItem));
        }
        return optionalAccount;
    }
}
