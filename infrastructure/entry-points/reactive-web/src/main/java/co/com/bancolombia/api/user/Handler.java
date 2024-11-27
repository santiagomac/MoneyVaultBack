package co.com.bancolombia.api.user;

import co.com.bancolombia.api.user.dto.JwtResponse;
import co.com.bancolombia.api.user.dto.LoginRequestDto;
import co.com.bancolombia.api.user.dto.RegisterRequest;
import co.com.bancolombia.api.user.dto.UserResponse;
import co.com.bancolombia.model.user.UserDto;
import co.com.bancolombia.usecase.user.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

  private final UserUseCase userUseCase;
  private final PasswordEncoder passwordEncoder;

  public Mono<ServerResponse> registerUser(ServerRequest serverRequest) {
    return serverRequest.bodyToMono(RegisterRequest.class)
        .flatMap(userRequest -> {
          UserDto userDto = UserDto.builder()
              .email(userRequest.getEmail())
              .password(passwordEncoder.encode(userRequest.getPassword()))
              .username(userRequest.getUsername())
              .build();
          return this.userUseCase.signUp(userDto);
        })
        .flatMap(userDto -> {
          UserResponse userResponse = UserResponse
              .builder()
              .id(userDto.getId())
              .username(userDto.getUsername())
              .email(userDto.getEmail())
              .build();
          return ServerResponse.ok().bodyValue(userResponse);
        })
        .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage()));
  }

  public Mono<ServerResponse> login(ServerRequest serverRequest) {
    return serverRequest.bodyToMono(LoginRequestDto.class)
        .flatMap(userRequest -> this.userUseCase.signIn(userRequest.getEmail(),
            userRequest.getPassword()))
        .flatMap(token -> {
          JwtResponse tokenGenerated = JwtResponse.builder()
              .token(token)
              .build();
          return ServerResponse.ok().bodyValue(tokenGenerated);
        })
        .onErrorResume(error -> ServerResponse.badRequest().bodyValue(error.getMessage()));
  }
}
