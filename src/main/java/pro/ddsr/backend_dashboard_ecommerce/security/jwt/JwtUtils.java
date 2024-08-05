package pro.ddsr.backend_dashboard_ecommerce.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
    @Value("${jwt.secret.key}")
    private String secretKey; // firma del token 

    @Value("${jwt.time.expiration}")
    private String timeExpiration; // tiempo de expiracion


    // generar token de acceso

    @SuppressWarnings("deprecation")
    public String generateAccesToken( String username){
        return Jwts.builder()
            .setSubject( username )
            .setIssuedAt( new Date( System.currentTimeMillis()))
            .setExpiration( new Date( System.currentTimeMillis() + Long.parseLong(timeExpiration.trim())))
            .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    // validar token
    @SuppressWarnings("deprecation")
    public Boolean isTokenValid(String token){
        System.out.println(getSignatureKey());
        try {
            // intenta parsear el token, si sale mal, se genera una excepcion
            Claims theToken = Jwts.parser()
                .setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
            
            System.out.println("CLAIMS: " + theToken);
            return true;
        } catch (Exception e) {
            System.out.println("Token invalido: " + e.getMessage() + token + " ----> " + e.getLocalizedMessage());
            return false;
        }
    }

    // obtener claims
    @SuppressWarnings("deprecation")
    public Claims extractAllClaims(String token){
        // intenta parsear el token, si sale mal, se genera una excepcion
        return Jwts.parser() // se seupone que se usa parserBuilder() per o arroja error
        .setSigningKey(getSignatureKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
    }

    // obtener el email del token 
    public String getUsernameFromUser(String token){
        return  this.getClaim(token, Claims::getSubject );
    }

    // obtener un solo claim 
    public <T> T getClaim(String token, Function<Claims, T> claimsFunction){
        // esto devuelve lo que retorne la funcion del parametro
        Claims  claims = extractAllClaims(token);
        return claimsFunction.apply(claims);    

    }

    // obtener firma del token
    public Key getSignatureKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey); // decodificarr clave
        return Keys.hmacShaKeyFor(keyBytes); 
        /*
         * convierte un arreglo de bytes en una clave secreta SecretKey 
         * que se puede usar para crear o verificar tokens JWT utilizando el algoritmo de firma HMAC-SHA.
        */
    }

}
