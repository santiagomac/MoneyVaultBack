package co.com.bancolombia.r2dbc.user;

import co.com.bancolombia.model.transaction.TransactionDto;
import co.com.bancolombia.model.transaction.gateways.TransactionGateway;
import co.com.bancolombia.model.user.UserDto;
import co.com.bancolombia.model.user.gateways.UserGateway;
import co.com.bancolombia.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class UserRepositoryAdapter extends ReactiveAdapterOperations<UserDto/* change for domain model */, UserEntity/* change for adapter model */, Long, UserRepository>
    implements UserGateway
{
    public UserRepositoryAdapter(UserRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, UserDto.class/* change for domain model */));
    }

    @Override
    public Mono<UserDto> createUser(UserDto userDto) {
        return this.save(userDto);
    }

    @Override
    public Mono<UserDto> findByEmail(String email) {
        return this.repository.findByEmail(email);
    }
}
