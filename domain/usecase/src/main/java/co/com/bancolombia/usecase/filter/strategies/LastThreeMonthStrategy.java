package co.com.bancolombia.usecase.filter.strategies;

import co.com.bancolombia.model.transaction.TransactionDto;
import co.com.bancolombia.model.transaction.gateways.TransactionGateway;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

public class LastThreeMonthStrategy implements DateFilterStrategy {
    @Override
    public Flux<TransactionDto> filter(TransactionGateway transactionGateway, LocalDateTime startDate) {
        return transactionGateway.filterTransactionsByLastThreeMonths(startDate);
    }
}
