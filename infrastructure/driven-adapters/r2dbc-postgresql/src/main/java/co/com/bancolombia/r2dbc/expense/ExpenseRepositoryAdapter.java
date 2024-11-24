package co.com.bancolombia.r2dbc.expense;

import co.com.bancolombia.model.expense.ExpenseDto;
import co.com.bancolombia.model.expense.gateways.ExpenseGateway;
import co.com.bancolombia.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ExpenseRepositoryAdapter extends ReactiveAdapterOperations<ExpenseDto/* change for domain model */, ExpenseEntity/* change for adapter model */, Long, ExpenseRepository>
implements ExpenseGateway
{
    public ExpenseRepositoryAdapter(ExpenseRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, ExpenseDto.class/* change for domain model */));
    }

    @Override
    public Mono<ExpenseDto> create(ExpenseDto expenseDto) {
        return this.save(expenseDto);
    }

    @Override
    public Mono<ExpenseDto> getById(Long id) {
        return this.findById(id);
    }

    @Override
    public Flux<ExpenseDto> getAllExpenses() {
        return this.findAll();
    }

    @Override
    public Mono<Void> update(ExpenseDto expenseDto) {
         return this.save(expenseDto).then();
    }

    @Override
    public Mono<Void> delete(Long id) {
        return this.repository.deleteById(id);
    }
}
