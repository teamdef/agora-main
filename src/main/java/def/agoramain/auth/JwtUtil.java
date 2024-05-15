package def.agoramain.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public Boolean isValidate(String token) {
        return !isTokenExpired(token);
    }

    public String getMemberId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) throws ExpiredJwtException{
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws ExpiredJwtException{
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) throws ExpiredJwtException {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        Boolean isBefore = Boolean.TRUE;
        try {
            isBefore = extractExpiration(token).before(new Date());
        } catch (ExpiredJwtException ex) {
            log.info(ex.getMessage());
        }
        return isBefore;
    }
}
