package co.com.bancolombia.model.user.gateways;

import co.com.bancolombia.model.user.UserDto;
import reactor.core.publisher.Mono;

public interface UserGateway {

    Mono<UserDto> createUser(UserDto userDto);
    Mono<UserDto> findByEmail(String email);
}
