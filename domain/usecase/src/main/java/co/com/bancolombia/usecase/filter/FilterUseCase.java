package co.com.bancolombia.usecase.filter;

import co.com.bancolombia.model.transaction.TransactionDto;
import co.com.bancolombia.model.transaction.gateways.TransactionGateway;
import co.com.bancolombia.usecase.filter.strategies.DateFilterStrategy;
import co.com.bancolombia.usecase.filter.strategies.LastThreeMonthStrategy;
import co.com.bancolombia.usecase.filter.strategies.PastMonthStrategy;
import co.com.bancolombia.usecase.filter.strategies.PastWeekStrategy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.Map;

@RequiredArgsConstructor
public class FilterUseCase {

    private final TransactionGateway transactionGateway;

    private final Map<String, DateFilterStrategy> filterStrategies = Map.of(
            "past_week", new PastWeekStrategy(),
            "past_month", new PastMonthStrategy(),
            "last_3_months", new LastThreeMonthStrategy()
    );

    public Flux<TransactionDto> filterTransactions(String period, String startDate, String endDate) {
        DateFilterStrategy strategy = period != null ? filterStrategies.get(period) : null;

        if (strategy != null) {
            return strategy.filter(transactionGateway, LocalDateTime.now());
        }

        if (startDate != null && endDate != null) {
            return this.getTransactionsByCustomPeriod(
                    LocalDateTime.parse(startDate),
                    LocalDateTime.parse(endDate)
            );
        }

        return Flux.empty();
    }

    private Flux<TransactionDto> getTransactionsByCustomPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        return this.transactionGateway.filterTransactionsByCustomPeriod(startDate, endDate);
    }
}