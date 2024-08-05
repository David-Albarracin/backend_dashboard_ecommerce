package pro.ddsr.backend_dashboard_ecommerce.security.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Account;
import pro.ddsr.backend_dashboard_ecommerce.security.jwt.JwtUtils;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private JwtUtils jwtUtils;
    
    public  JwtAuthenticationFilter(  JwtUtils jwtUtils){
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        
        
        Account account = null;
        String email = "";
        String password = "";
        
       
        // parsea el json de la request a un objeto tipo Account
        try {
            account = new ObjectMapper().readValue(request.getInputStream(), Account.class);

            // obtiene email y password de la entidad
            email = account.getUsername();
            password = account.getPassword();
            System.out.println("Attempting authentication with email: " + email);


        } catch (StreamReadException e) {
            System.out.println("Error en la lectura: " +  e.getCause());
        } catch (DatabindException e) {
            System.out.println("Error en la databind: " +  e.getCause());
        } catch (IOException e) {
            System.out.println("Error en IOException: " +  e.getCause());
        }
    
        // autenticacion
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(email, password);

        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        
            User user =  (User) authResult.getPrincipal(); // se obtiene el objeto de la autenticacion

            // generacion de token de acceso
            String token = jwtUtils.generateAccesToken(user.getUsername());

            response.addHeader("Authorization", token);

            Map<String, Object> httpResponse = new HashMap<>();
            
            httpResponse.put("token", token);
            httpResponse.put("Message", "Autenticacion correcta");
            httpResponse.put("username: ", user.getUsername());

            // Convierte el mapa a JSON y escribe en el cuerpo de la respuesta

            response.getWriter().write( new ObjectMapper().writeValueAsString(httpResponse));
            response.setContentType("application/json");
            response.setStatus(200);
            response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }
    
}
