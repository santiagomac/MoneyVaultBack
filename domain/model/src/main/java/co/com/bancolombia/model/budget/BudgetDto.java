package co.com.bancolombia.model.budget;
import co.com.bancolombia.model.transaction.TransactionDto;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BudgetDto {

    private Long id;
    private String title;
    private List<TransactionDto> transactions;
    private Double totalExpenses;
    private Double totalIncomes;
}
