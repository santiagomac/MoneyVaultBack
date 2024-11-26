package co.com.bancolombia.r2dbc.user;

import co.com.bancolombia.model.transaction.TransactionDto;
import co.com.bancolombia.model.user.UserDto;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<UserEntity, Long>, ReactiveQueryByExampleExecutor<UserEntity> {

    Mono<UserDto> findByEmail(String email);
}
