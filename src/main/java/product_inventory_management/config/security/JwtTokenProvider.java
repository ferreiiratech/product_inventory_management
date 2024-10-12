package product_inventory_management.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenProvider {
    @Value("${app.security.jwt.jwtSecretKey}")
    String jwtSecret;
    @Value("${app.security.jwt.expirationDays}")
    int expirationDays;
    @Value("${app.security.jwt.zoneOffset}")
    String zoneOffset;
    @Value("${app.security.jwt.issuer}")
    String issuer;


    public String generateToken(String username) {
        try {
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(username)
                    .withIssuedAt(new Date())
                    .withExpiresAt(genExpirationDate())
                    .sign(getAlgorithm());
        } catch (JWTCreationException exception) {
            throw new RuntimeException("JWT generation failed", exception);
        }
    }

    public Optional<String> validateToken(String token) {
        try {
            var jwt =  JWT.require(getAlgorithm())
                    .withIssuer(this.issuer)
                    .build()
                    .verify(token)
                    .getSubject();

            return Optional.of(jwt);
        } catch (JWTVerificationException exception) {
            return Optional.empty();
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusDays(this.expirationDays).toInstant(ZoneOffset.of(this.zoneOffset));
    }

    private Algorithm getAlgorithm() {
        if(this.jwtSecret == null){
            throw new RuntimeException("JWT Secret Key not found");
        }

        return Algorithm.HMAC256(this.jwtSecret);
    }
}
