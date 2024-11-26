package co.com.bancolombia.api.user;

import co.com.bancolombia.api.config.JwtService;
import co.com.bancolombia.api.user.dto.LoginRequestDto;
import co.com.bancolombia.api.user.dto.RegisterRequest;
import co.com.bancolombia.api.user.dto.UserResponse;
import co.com.bancolombia.model.user.UserDto;
import co.com.bancolombia.usecase.user.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final UserUseCase userUseCase;
    private final ReactiveAuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public Mono<ServerResponse> registerUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(RegisterRequest.class)
                .flatMap(userRequest -> {
                    UserDto userDto = UserDto.builder()
                            .email(userRequest.getEmail())
                            .password(passwordEncoder.encode(userRequest.getPassword()))
                            .username(userRequest.getUsername())
                            .build();
                    return this.userUseCase.registerUser(userDto);
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

    public Mono<ServerResponse> listenGETOtherUseCase(ServerRequest serverRequest) {
        // useCase2.logic();
        return ServerResponse.ok().bodyValue("");
    }

    public Mono<ServerResponse> login(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(LoginRequestDto.class)
                .flatMap(loginRequest ->
                        authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
                        ).doOnNext(auth -> System.out.println("Authentication successful: " + auth.getName()))
                )
                .flatMap(auth -> {
                    String token = jwtService.generateToken(auth.getName());
                    System.out.println("Generated token: " + token);

                    return ServerResponse.ok().bodyValue(token);
                })
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("Error: " + e.getMessage()));
    }
}
