
package pro.ddsr.backend_dashboard_ecommerce.account.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import pro.ddsr.backend_dashboard_ecommerce.account.domain.dto.AccountDto;
import pro.ddsr.backend_dashboard_ecommerce.account.domain.repository.AccountRepository;
import pro.ddsr.backend_dashboard_ecommerce.account.persistence.Account;

@Service
public class AccountService {
    // Define service methods here
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    
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

    // convertir de dto a entidad
    public Account toEntity( AccountDto dto){

        //  contruccion de accounbt
        return Account.builder()
            .username(dto.getUsername())
            .password( passwordEncoder.encode(dto.getPassword() ) )
            .roles(dto.getRoles())
            .build();
    }

    public Optional<Account> findById(Long id) {
        return this.accountRepository.findById(id);
    }

    public Account save(AccountDto dto) {
        Account account = this.toEntity(dto);
        
        return this.accountRepository.save(account);
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
