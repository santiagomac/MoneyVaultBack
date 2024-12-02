package co.com.bancolombia.r2dbc.transaction;

import co.com.bancolombia.model.transaction.TransactionDto;
import co.com.bancolombia.model.transaction.gateways.TransactionGateway;
import co.com.bancolombia.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
public class TransactionRepositoryAdapter extends ReactiveAdapterOperations<TransactionDto, TransactionEntity, Long, TransactionRepository>
        implements TransactionGateway {
    public TransactionRepositoryAdapter(TransactionRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, TransactionDto.class/* change for domain model */));
    }

    @Override
    public Mono<TransactionDto> create(TransactionDto transactionDto) {
        return this.save(transactionDto);
    }

    @Override
    public Mono<TransactionDto> getById(Long id, Long userId) {
        return this.repository.findByIdAndUserId(id, userId);
    }

    @Override
    public Flux<TransactionDto> getAllExpenses(Long userId) {
        return this.repository.findByUserId(userId);
    }

    @Override
    public Mono<Void> update(TransactionDto transactionDto) {
        return this.save(transactionDto).then();
    }

    @Override
    public Mono<Void> delete(Long id) {
        return this.repository.deleteById(id);
    }

    @Override
    public Flux<TransactionDto> getExpensesByBudgetId(Long id) {
        return this.repository.findByBudgetId(id);
    }

    @Override
    public Flux<TransactionDto> filterTransactionsByLastWeek(LocalDateTime currentDate) {
        return this.repository.filterTransactionsByLastWeek(currentDate)
                .map(transaction -> this.mapper.map(transaction, TransactionDto.class));
    }

    @Override
    public Flux<TransactionDto> filterTransactionsByLastMonth(LocalDateTime currentDate) {
        return this.repository.filterTransactionsByLastMonth(currentDate)
                .map(transaction -> this.mapper.map(transaction, TransactionDto.class));
    }

    @Override
    public Flux<TransactionDto> filterTransactionsByLastThreeMonths(LocalDateTime currentDate) {
        return this.repository.filterTransactionsByLastThreeMonths(currentDate)
                .map(transaction -> this.mapper.map(transaction, TransactionDto.class));
    }

    @Override
    public Flux<TransactionDto> filterTransactionsByCustomPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        return this.repository.filterTransactionsByCustomPeriod(startDate, endDate)
                .map(transaction -> this.mapper.map(transaction, TransactionDto.class));
    }
}
