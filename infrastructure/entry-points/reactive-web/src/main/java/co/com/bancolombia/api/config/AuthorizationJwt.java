package co.com.bancolombia.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class AuthorizationJwt {


  private final SecurityContextRepository securityContextRepository;

  public AuthorizationJwt(SecurityContextRepository securityContextRepository) {
    this.securityContextRepository = securityContextRepository;
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityWebFilterChain filterChain(ServerHttpSecurity http,
      JwtFilter jwtFilter) {
    http
        .csrf(ServerHttpSecurity.CsrfSpec::disable)
        .authorizeExchange(authorize -> authorize
            .pathMatchers("/api/v1/auth/**")
            .permitAll()
            .anyExchange().authenticated())
        .addFilterAfter(jwtFilter, SecurityWebFiltersOrder.FIRST)
        .securityContextRepository(securityContextRepository)
        .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
        .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
        .logout(ServerHttpSecurity.LogoutSpec::disable);

    return http.build();
  }
}
