package co.com.bancolombia.model.budget.gateways;

import co.com.bancolombia.model.budget.BudgetDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BudgetGateway {

    Mono<BudgetDto> createBudget(BudgetDto budgetDto);
    Flux<BudgetDto> findAllBudgets();
    Mono<BudgetDto> getBudgetById(Long id);
    Mono<Void> updateBudget(BudgetDto budgetDto);
    Mono<Void> deleteBudgetById(Long id);
}
