package co.com.bancolombia.r2dbc.budget;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

// TODO: This file is just an example, you should delete or modify it
public interface BudgetRepository extends ReactiveCrudRepository<BudgetEntity, Long>, ReactiveQueryByExampleExecutor<BudgetEntity> {

  Flux<BudgetEntity> findAllByUserId(Long userId);
}
