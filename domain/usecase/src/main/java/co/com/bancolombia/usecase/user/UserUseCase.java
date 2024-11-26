package co.com.bancolombia.usecase.user;

import co.com.bancolombia.model.user.UserDto;
import co.com.bancolombia.model.user.gateways.UserGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserGateway userGateway;

    public Mono<UserDto> registerUser(UserDto userDto) {
        return userGateway.findByEmail(userDto.getEmail())
                .flatMap(existingUser -> Mono.<UserDto>error(new IllegalStateException("Username already exists")))
                .switchIfEmpty(Mono.defer(() -> this.userGateway.createUser(userDto)));
    }


}
