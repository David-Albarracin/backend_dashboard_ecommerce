package pro.ddsr.backend_dashboard_ecommerce;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import pro.ddsr.backend_dashboard_ecommerce.account.domain.repository.AccountRepository;
import pro.ddsr.backend_dashboard_ecommerce.account.persistence.Account;
import pro.ddsr.backend_dashboard_ecommerce.permission.domain.repository.PermissionRepository;
import pro.ddsr.backend_dashboard_ecommerce.permission.persistence.Permission;
import pro.ddsr.backend_dashboard_ecommerce.role.domain.repository.RoleRepository;
import pro.ddsr.backend_dashboard_ecommerce.role.persistence.Role;
import pro.ddsr.backend_dashboard_ecommerce.role.persistence.RoleEnum;

@SpringBootApplication
public class BackendDashboardEcommerceApplication {

	@Autowired
	PasswordEncoder encoder;

	@Autowired 
	AccountRepository accountRepository;

	@Autowired 
	PermissionRepository permissionRepository;

	@Autowired 
	RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendDashboardEcommerceApplication.class, args);
	}


	@Bean
	CommandLineRunner init(){

		return args -> {

			// Crear permisos
			Permission readPermission = new Permission();
			readPermission.setName("READ");

			Permission writePermission = new Permission();
			writePermission.setName("WRITE");

			// Guardar permisos en la base de datos (si no están ya guardados)
			permissionRepository.save(readPermission);
			permissionRepository.save(writePermission);

			// Crear rol y agregar permisos
			Role adminRole = new Role();
			adminRole.setName(RoleEnum.ADMIN);
			adminRole.setPermissions(Set.of(readPermission, writePermission));

			// Guardar rol en la base de datos
			//roleRepository.save(adminRole);

			// Role role = new Role();
			// role.setName(
			// 	RoleEnum.ADMIN
			// );	
			Account testAccount = new Account();
			testAccount.setUsername("edgar");
			testAccount.setPassword(encoder.encode("1234"));
			testAccount.setRoles(Set.of(adminRole));
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
