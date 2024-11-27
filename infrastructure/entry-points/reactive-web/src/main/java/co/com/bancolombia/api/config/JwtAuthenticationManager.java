package co.com.bancolombia.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

  private final JwtProvider jwtProvider;

  @Override
  public Mono<Authentication> authenticate(Authentication authentication) {
    return Mono.just(authentication)
        .map(auth -> jwtProvider.getClaims(auth.getCredentials().toString()))
        .log()
        .onErrorResume(e -> Mono.error(new Throwable("bad token")))
        .map(claims -> new UsernamePasswordAuthenticationToken(
            claims.getSubject(),
            null,
            null
        ));
  }
}
