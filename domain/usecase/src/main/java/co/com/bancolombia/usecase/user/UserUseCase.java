package co.com.bancolombia.usecase.user;

import co.com.bancolombia.model.jwt.JwtGateway;
import co.com.bancolombia.model.user.UserDto;
import co.com.bancolombia.model.user.gateways.UserGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserGateway userGateway;
    private final PasswordValidator passwordValidator;
    private final JwtGateway jwtGateway;

    public Mono<UserDto> signUp(UserDto user) {
        return userGateway.findByEmail(user.getUsername())
            .flatMap(existingUser -> Mono.<UserDto>error(new IllegalStateException("User already exists")))
            .switchIfEmpty(Mono.defer(() -> userGateway.createUser(user)));
    }

    // Sign In (Login)
    public Mono<String> signIn(String email, String password) {
        return userGateway.findByEmail(email)
            .filter(user -> this.passwordValidator.validatePassword(password, user.getPassword()))
            .map(this.jwtGateway::generateToken)
            .switchIfEmpty(Mono.error(new IllegalStateException("Invalid email or password")));
    }


}
