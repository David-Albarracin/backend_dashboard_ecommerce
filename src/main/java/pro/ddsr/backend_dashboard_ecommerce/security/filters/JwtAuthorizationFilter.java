package pro.ddsr.backend_dashboard_ecommerce.security.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pro.ddsr.backend_dashboard_ecommerce.security.UserDetailsServiceImpl;
import pro.ddsr.backend_dashboard_ecommerce.security.jwt.JwtUtils;

@Component
public class JwtAuthorizationFilter  extends OncePerRequestFilter{
     @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, 
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        
        String tokenHeader = request.getHeader("Authorization");
        
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")){
            
            String token = tokenHeader.substring(7);
            

            if (jwtUtils.isTokenValid(token)){
                String email = jwtUtils.getUsernameFromUser(token);
                System.out.println(email);
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(email); 

                UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(email,null,  userDetails.getAuthorities());


            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
        
    }
}
