package co.com.bancolombia.usecase.user;

import co.com.bancolombia.model.user.UserDto;
import co.com.bancolombia.model.user.gateways.UserGateway;
import co.com.bancolombia.usecase.exceptions.BadCredentialsLoginException;
import co.com.bancolombia.usecase.user.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase {

  private final UserGateway userGateway;
  private final PasswordValidator passwordValidator;
  private final JwtGateway jwtGateway;

  public Mono<UserDto> signUp(UserDto user) {
    return userGateway.findByEmail(user.getUsername())
        .flatMap(
            existingUser -> Mono.<UserDto>error(new IllegalStateException("User already exists")))
        .switchIfEmpty(Mono.defer(() -> userGateway.createUser(user)));
  }

  // Sign In (Login)
  public Mono<LoginDto> signIn(String email, String password) {
    return userGateway.findByEmail(email)
        .filter(user -> this.passwordValidator.validatePassword(password, user.getPassword()))
        .map(this::buildLoginDto)
        .switchIfEmpty(Mono.error(new BadCredentialsLoginException("Invalid email or password")));
  }

  private LoginDto buildLoginDto(UserDto user) {
    return LoginDto
        .builder()
        .userDto(user)
        .token(this.jwtGateway.generateToken(user))
        .build();
  }


}
