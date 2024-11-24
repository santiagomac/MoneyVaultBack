package co.com.bancolombia.usecase.expense;

import co.com.bancolombia.model.expense.ExpenseDto;
import co.com.bancolombia.model.expense.gateways.ExpenseGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ExpenseUseCase {

    private final ExpenseGateway expenseGateway;

    public Mono<ExpenseDto> createExpense(ExpenseDto expenseDto) {
        return this.expenseGateway.create(expenseDto);
    }

    public Flux<ExpenseDto> getAllExpenses() {
        return expenseGateway.getAllExpenses();
    }

    public Mono<ExpenseDto> getExpense(Long id) {
        return this.expenseGateway.getById(id);
    }

    public Mono<Void> updateExpense(Long id, ExpenseDto expenseDto) {
        expenseDto.setId(id);
        return this.expenseGateway.update(expenseDto);
    }

    public Mono<Void> deleteExpense(Long id) {
        return this.expenseGateway.delete(id);
    }
}
