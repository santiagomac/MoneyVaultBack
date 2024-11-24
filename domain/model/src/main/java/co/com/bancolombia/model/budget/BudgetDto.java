package co.com.bancolombia.model.budget;
import co.com.bancolombia.model.expense.ExpenseDto;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.core.publisher.Flux;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BudgetDto {

    private Long id;
    private String title;
    private Flux<ExpenseDto> expenses;
    private Double totalExpenses;
    private Double totalIncomes;
}
