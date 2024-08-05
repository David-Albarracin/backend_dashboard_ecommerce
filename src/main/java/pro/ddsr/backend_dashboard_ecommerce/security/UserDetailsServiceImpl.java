package pro.ddsr.backend_dashboard_ecommerce.security;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pro.ddsr.backend_dashboard_ecommerce.domain.repository.AccountRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Account;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
     @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        
        Optional<Account> account = accountRepository.findByUsername(userName);

        if (account.isEmpty()){
            throw( new UsernameNotFoundException("El usuario con el usuario ingresado no existe"));  
        }

        Account foundAccount = account.get();
        

        Collection<? extends GrantedAuthority> authorities = foundAccount.getRoles()
            .stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getName().name())))
            .collect(Collectors.toSet());

        
        
        return new User(
            foundAccount.getUsername(),
            foundAccount.getPassword(),
            true,
            true,
            true,
            true,
            authorities
        );
    }
}
