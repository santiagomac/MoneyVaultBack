package co.com.bancolombia.model.expense;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class ExpenseDto {

    private Long id;
    private String title;
    private Double amount;
    private String category;
    private LocalDateTime date;
    private Long budgetId;
    private String movementType;
}
