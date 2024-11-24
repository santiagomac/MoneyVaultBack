package co.com.bancolombia.config;

import co.com.bancolombia.model.budget.gateways.BudgetGateway;
import co.com.bancolombia.model.expense.gateways.ExpenseGateway;
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
    public ExpenseUseCase expenseUseCase(ExpenseGateway expenseGateway) {
        return new ExpenseUseCase(expenseGateway);
    }

    @Bean
    public BudgetUseCase budgetUseCase(BudgetGateway budgetGateway) {
        return new BudgetUseCase(budgetGateway);
    }
}
