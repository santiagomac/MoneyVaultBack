package co.com.bancolombia.r2dbc.budget;

import co.com.bancolombia.model.budget.BudgetDto;
import co.com.bancolombia.model.budget.gateways.BudgetGateway;
import co.com.bancolombia.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class BudgetRepositoryAdapter extends
    ReactiveAdapterOperations<BudgetDto, BudgetEntity, Long, BudgetRepository>
    implements BudgetGateway {

  public BudgetRepositoryAdapter(BudgetRepository repository, ObjectMapper mapper) {
    super(repository, mapper, d -> mapper.map(d, BudgetDto.class/* change for domain model */));
  }

  @Override
  public Mono<BudgetDto> createBudget(BudgetDto budgetDto) {
    return this.save(budgetDto);
  }

  @Override
  public Flux<BudgetDto> findAllBudgets(Long userId) {
    return this.repository.findAllByUserId(userId)
        .map(budgetEntity -> this.mapper
            .map(budgetEntity, BudgetDto.class));
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
