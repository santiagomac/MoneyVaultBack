package co.com.bancolombia.api.config;

import co.com.bancolombia.model.user.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.util.Date;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

  private static final Logger LOGGER = Logger.getLogger(JwtProvider.class.getName());

  private static final String SECRET_KEY = "YOURSECRETKEYLLENOESTOPORQUENECESITMASCARACTERESPARAQUEESTONOLLORE";
  private static final long EXPIRATION_TIME = 86400000L; // 24 hours

  public String generateToken(UserDto user) {
    return Jwts.builder()
        .subject(user.getEmail())
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(getKey(SECRET_KEY))
        .compact();
  }

  public Claims getClaims(String token) {
    return Jwts.parser()
        .verifyWith(getKey(SECRET_KEY))
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  public String getSubject(String token) {
    return Jwts.parser()
        .verifyWith(getKey(SECRET_KEY))
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getSubject();
  }

  public boolean validate(String token) {
    try {
      Jwts.parser()
          .verifyWith(getKey(SECRET_KEY))
          .build()
          .parseSignedClaims(token)
          .getPayload()
          .getSubject();
      return true;
    } catch (ExpiredJwtException e) {
      LOGGER.severe("token expired");
    } catch (UnsupportedJwtException e) {
      LOGGER.severe("token unsupported");
    } catch (MalformedJwtException e) {
      LOGGER.severe("token malformed");
    } catch (SignatureException e) {
      LOGGER.severe("bad signature");
    } catch (IllegalArgumentException e) {
      LOGGER.severe("illegal args");
    }
    return false;
  }

  private SecretKey getKey(String secret) {
    byte[] secretBytes = Decoders.BASE64URL.decode(secret);
    return Keys.hmacShaKeyFor(secretBytes);
  }
}
