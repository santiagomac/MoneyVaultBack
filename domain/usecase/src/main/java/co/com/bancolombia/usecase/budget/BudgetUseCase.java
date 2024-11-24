package co.com.bancolombia.usecase.budget;

import co.com.bancolombia.model.budget.BudgetDto;
import co.com.bancolombia.model.budget.gateways.BudgetGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BudgetUseCase {

    private final BudgetGateway budgetGateway;

    public Mono<BudgetDto> createBudget(BudgetDto budgetDto) {
        return this.budgetGateway.createBudget(budgetDto);
    }

    public Flux<BudgetDto> getAllBudgets(){
        return this.budgetGateway.findAllBudgets();
    }

    public Mono<BudgetDto> getBudgetById(Long id) {
        return this.budgetGateway.getBudgetById(id);
    }

    public Mono<Void> updateBudget(Long id, BudgetDto budgetDto) {
        budgetDto.setId(id);
        return this.budgetGateway.updateBudget(budgetDto);
    }

    public Mono<Void> deleteBudget(Long id) {
        return this.budgetGateway.deleteBudgetById(id);
    }
}
