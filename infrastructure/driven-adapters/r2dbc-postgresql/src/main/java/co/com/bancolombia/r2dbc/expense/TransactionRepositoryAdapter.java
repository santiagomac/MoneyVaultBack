package co.com.bancolombia.r2dbc.expense;

import co.com.bancolombia.model.transaction.TransactionDto;
import co.com.bancolombia.model.transaction.gateways.TransactionGateway;
import co.com.bancolombia.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class TransactionRepositoryAdapter extends ReactiveAdapterOperations<TransactionDto/* change for domain model */, ExpenseEntity/* change for adapter model */, Long, ExpenseRepository>
implements TransactionGateway
{
    public TransactionRepositoryAdapter(ExpenseRepository repository, ObjectMapper mapper) {
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
    public Mono<TransactionDto> getById(Long id) {
        return this.findById(id);
    }

    @Override
    public Flux<TransactionDto> getAllExpenses() {
        return this.findAll();
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
}
