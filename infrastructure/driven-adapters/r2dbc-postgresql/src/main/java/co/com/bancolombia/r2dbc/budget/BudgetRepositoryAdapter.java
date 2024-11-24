package co.com.bancolombia.r2dbc.budget;

import co.com.bancolombia.model.budget.BudgetDto;
import co.com.bancolombia.model.budget.gateways.BudgetGateway;
import co.com.bancolombia.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class BudgetRepositoryAdapter extends ReactiveAdapterOperations<BudgetDto/* change for domain model */, BudgetEntity/* change for adapter model */, Long, BudgetRepository>
        implements BudgetGateway {
    public BudgetRepositoryAdapter(BudgetRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, BudgetDto.class/* change for domain model */));
    }

    @Override
    public Mono<BudgetDto> createBudget(BudgetDto budgetDto) {
        return this.save(budgetDto);
    }

    @Override
    public Flux<BudgetDto> findAllBudgets() {
        return this.findAll();
    }

    @Override
    public Mono<BudgetDto> getBudgetById(Long id) {
        return this.findById(id);
    }

    @Override
    public Mono<Void> updateBudget(BudgetDto budgetDto) {
        return this.save(budgetDto).then();
    }

    @Override
    public Mono<Void> deleteBudgetById(Long id) {
        return this.deleteBudgetById(id);
    }
}
