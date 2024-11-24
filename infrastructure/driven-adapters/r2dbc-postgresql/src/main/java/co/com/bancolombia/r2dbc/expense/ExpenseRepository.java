package co.com.bancolombia.r2dbc.expense;

import co.com.bancolombia.model.transaction.TransactionDto;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ExpenseRepository extends ReactiveCrudRepository<ExpenseEntity, Long>, ReactiveQueryByExampleExecutor<ExpenseEntity> {

    Flux<TransactionDto> findByBudgetId(Long budgetId);
}
