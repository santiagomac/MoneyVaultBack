package co.com.bancolombia.r2dbc.transaction;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table("transactions")
public class TransactionEntity {

    @Id
    private Long id;
    private String title;
    private Double amount;
    private String category;
    private LocalDateTime date;
    private Long budgetId;
    private String movementType;
    private Long userId;
}
