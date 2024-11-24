package co.com.bancolombia.usecase.budget;

import co.com.bancolombia.model.budget.BudgetDto;
import co.com.bancolombia.model.budget.gateways.BudgetGateway;
import co.com.bancolombia.model.transaction.TransactionDto;
import co.com.bancolombia.usecase.expense.ExpenseUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class BudgetUseCase {

    private final BudgetGateway budgetGateway;
    private final ExpenseUseCase expenseUseCase;

    public Mono<BudgetDto> createBudget(BudgetDto budgetDto) {
        return this.budgetGateway.createBudget(budgetDto);
    }

    public Flux<BudgetDto> getAllBudgets() {
        return this.budgetGateway.findAllBudgets()
                .flatMap(budget -> this.getExpensesForBudget(budget.getId())
                        .map(expenses -> calculateAndSetBudgetTotals(budget, expenses)));
    }

    public Mono<BudgetDto> getBudgetById(Long id) {
        return this.budgetGateway.getBudgetById(id)
                .flatMap(budget -> this.getExpensesForBudget(budget.getId())
                        .map(expenses -> calculateAndSetBudgetTotals(budget, expenses)));
    }

    public Mono<Void> updateBudget(Long id, BudgetDto budgetDto) {
        budgetDto.setId(id);
        return this.budgetGateway.updateBudget(budgetDto);
    }

    public Mono<Void> deleteBudget(Long id) {
        return this.budgetGateway.deleteBudgetById(id);
    }


    private Mono<List<TransactionDto>> getExpensesForBudget(Long budgetId) {
        return this.expenseUseCase.getExpensesByBudgetId(budgetId)
                .collectList();
    }

    private BudgetDto calculateAndSetBudgetTotals(BudgetDto budgetDto, List<TransactionDto> expenses) {
        double totalIncomes = this.calculateTotalByMovementType(expenses, "income");
        double totalExpenses = this.calculateTotalByMovementType(expenses, "expense");

        budgetDto.setTotalIncomes(totalIncomes);
        budgetDto.setTotalExpenses(totalExpenses);
        budgetDto.setTransactions(expenses);
        return budgetDto;
    }

    private double calculateTotalByMovementType(List<TransactionDto> expenses, String movementType) {
        return expenses.stream()
                .filter(expense -> movementType.equals(expense.getMovementType()))
                .mapToDouble(TransactionDto::getAmount)
                .sum();
    }
}
