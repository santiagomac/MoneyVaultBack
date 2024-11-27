package co.com.bancolombia.api.config;

import co.com.bancolombia.model.user.gateways.UserGateway;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtFilter implements WebFilter {

  private final JwtProvider jwtProvider;
  private final UserGateway userGateway;

  private static final List<String> EXCLUDED_PATHS = List.of("/api/v1/auth/signup",
      "/api/v1/auth/signin");

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    ServerHttpRequest request = exchange.getRequest();
    String path = request.getPath().value();
    if (path.contains("auth")) {
      return chain.filter(exchange);
    }

    String auth = request.getHeaders().getFirst("Authorization");
    if (auth == null) {
      return Mono.error(new Throwable("No token was found"));
    }

    if (!auth.startsWith("Bearer ")) {
      return Mono.error(new Throwable("Invalid token"));
    }

    String token = auth.replace("Bearer ", "");
    exchange.getAttributes().put("token", token);
    return chain.filter(exchange);
  }

}
