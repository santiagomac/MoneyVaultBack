package co.com.bancolombia.config;

import co.com.bancolombia.model.budget.gateways.BudgetGateway;
import co.com.bancolombia.model.transaction.gateways.TransactionGateway;
import co.com.bancolombia.usecase.budget.BudgetUseCase;
import co.com.bancolombia.usecase.expense.ExpenseUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "co.com.bancolombia.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {

    @Bean
    public ExpenseUseCase expenseUseCase(TransactionGateway transactionGateway) {
        return new ExpenseUseCase(transactionGateway);
    }

    @Bean
    public BudgetUseCase budgetUseCase(BudgetGateway budgetGateway, ExpenseUseCase expenseUseCase) {
        return new BudgetUseCase(budgetGateway, expenseUseCase);
    }
}
