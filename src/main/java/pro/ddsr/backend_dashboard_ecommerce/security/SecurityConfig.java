package pro.ddsr.backend_dashboard_ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import pro.ddsr.backend_dashboard_ecommerce.security.filters.JwtAuthenticationFilter;
import pro.ddsr.backend_dashboard_ecommerce.security.filters.JwtAuthorizationFilter;
import pro.ddsr.backend_dashboard_ecommerce.security.jwt.JwtUtils;

@Configuration // clase de configuracion
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {
    
     @Autowired
    private JwtUtils jwtUtils; 

    // inyecta el servicio para extraer permisos y usuarios de la base de datos
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    // inyectar configuracion de la autenticacion
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;
    
     // conf de filtros de seguridad, el parametro representa al objeto http que pasa por los filtros
    @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception{

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");


         return httpSecurity
             // configuracion csrf
            .csrf( csrf -> csrf.disable())
            .httpBasic(Customizer.withDefaults())
            //configurar sesion stateless (la sesion del usuario no se guarda en memoria)
            .sessionManagement( sesion -> sesion.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(http -> {
                // publico
                http.requestMatchers(HttpMethod.POST, "/login").permitAll(); /*.hasAnyRole("ADMIN", "USER")*/
                http.requestMatchers(HttpMethod.POST, "/create-user").permitAll();
                // autenticado
                 
                http.anyRequest().authenticated();
            })
            .addFilter(jwtAuthenticationFilter)
            .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
            .build()
            ;
    }

    

    // conf del administrador de autenticaciones
    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        return this.authenticationConfiguration.getAuthenticationManager();
    }


    //conf del proovedor de autenticacion 
    @Bean
    public AuthenticationProvider authenticationProvider(){

        // proovedor de autenticacion con mediante usuario y contraseña de base de datos
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder()); // encriptacion de la contraseña
        provider.setUserDetailsService(userDetailsServiceImpl); // servicio que extrae el user y la contraseña
        
        return provider; 
    }


    // CONFIGURACION DE ENCRIPTACIÓN
    @Bean
    public PasswordEncoder passwordEncoder(){
        // return NoOpPasswordEncoder.getInstance(); // para testeo (retorna un passwordEncoder)
        return new BCryptPasswordEncoder(); //algoritmo de encriptaciónn
    }
}
