package co.com.bancolombia.r2dbc.expense;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ExpenseRepository extends ReactiveCrudRepository<ExpenseEntity, Long>, ReactiveQueryByExampleExecutor<ExpenseEntity> {
}
