package pro.ddsr.backend_dashboard_ecommerce;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import pro.ddsr.backend_dashboard_ecommerce.domain.repository.AccountRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Account;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Role;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.enumObj.RoleEnum;

@SpringBootApplication
public class BackendDashboardEcommerceApplication {

	@Autowired
	PasswordEncoder encoder;

	@Autowired 
	AccountRepository accountRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendDashboardEcommerceApplication.class, args);
	}


	@Bean
	CommandLineRunner init(){

		return args -> {

			Role role = new Role();
			role.setName(
				RoleEnum.ADMIN
			);	
			Account testAccount = new Account();
			testAccount.setUsername("edgar");
			testAccount.setPassword(encoder.encode("1234"));
			testAccount.setRoles(Set.of(role));
			// Obtener el conjunto de roles
			Set<Role> roles = testAccount.getRoles();

			System.out.println("HHHHHHHHHHHEYYYYYYYYYYYYYY ----> ");
			// Imprimir cada rol
			for (Role rolet : roles) {
				System.out.println("Role: " + rolet.getName()); // Asumiendo que `getName()` devuelve el nombre del rol
}
			
			
			accountRepository.save(testAccount);


			Role role2 = new Role();
			role2.setName(
				RoleEnum.USER
			);	
			Account testAccount2 = new Account();
			testAccount2.setUsername("sofia");
			testAccount2.setPassword(encoder.encode("12345"));
			testAccount2.setRoles(Set.of(role2));
	
			accountRepository.save(testAccount2);
		};
	}

}
