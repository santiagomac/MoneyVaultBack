//package co.com.bancolombia.api.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Mono;
//
//@Component
//@RequiredArgsConstructor
//public class SecurityHeadersConfig implements WebFilter {
//
//    private final JwtService jwtService;
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7);
//            jwtService.validateToken(token)
//                    .flatMap(username -> {
//                        SecurityContextHolder.getContext().setAuthentication(
//                                new UsernamePasswordAuthenticationToken(username, null, null)
//                        );
//
//                        return Mono.empty();
//                    });
//
//
//        }
//        return chain.filter(exchange);
//    }
//}
