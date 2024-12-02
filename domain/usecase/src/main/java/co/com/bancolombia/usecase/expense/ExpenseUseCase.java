package co.com.bancolombia.usecase.expense;

import co.com.bancolombia.model.transaction.TransactionDto;
import co.com.bancolombia.model.transaction.gateways.TransactionGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ExpenseUseCase {

    private final TransactionGateway transactionGateway;

    public Mono<TransactionDto> createExpense(TransactionDto transactionDto) {
        return this.transactionGateway.create(transactionDto);
    }

    public Flux<TransactionDto> getAllExpenses(Long userId) {
        return transactionGateway.getAllExpenses(userId);
    }

    public Flux<TransactionDto> getExpensesByBudgetId(Long budgetId) {
        return this.transactionGateway.getExpensesByBudgetId(budgetId);
    }

    public Mono<TransactionDto> getExpense(Long id, Long userId) {
        return this.transactionGateway.getById(id, userId);
    }

    public Mono<Void> updateExpense(Long id, TransactionDto transactionDto) {
        transactionDto.setId(id);
        return this.transactionGateway.update(transactionDto);
    }

    public Mono<Void> deleteExpense(Long id) {
        return this.transactionGateway.delete(id);
    }
}
