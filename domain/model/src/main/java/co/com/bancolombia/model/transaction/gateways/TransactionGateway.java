package co.com.bancolombia.model.expense.gateways;

import co.com.bancolombia.model.expense.ExpenseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExpenseGateway {

    Mono<ExpenseDto> create(ExpenseDto expenseDto);
    Mono<ExpenseDto> getById(Long id);
    Flux<ExpenseDto> getAllExpenses();
    Mono<Void> update(ExpenseDto expenseDto);
    Mono<Void> delete(Long id);
    Flux<ExpenseDto> getExpensesByBudgetId(Long id);
}
