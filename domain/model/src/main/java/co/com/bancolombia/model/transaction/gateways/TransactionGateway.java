package co.com.bancolombia.model.transaction.gateways;

import co.com.bancolombia.model.transaction.TransactionDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface TransactionGateway {

    Mono<TransactionDto> create(TransactionDto transactionDto);
    Mono<TransactionDto> getById(Long id, Long userId);
    Flux<TransactionDto> getAllExpenses(Long userId);
    Mono<Void> update(TransactionDto transactionDto);
    Mono<Void> delete(Long id);
    Flux<TransactionDto> getExpensesByBudgetId(Long id);
    Flux<TransactionDto> filterTransactionsByLastWeek(LocalDateTime currentDate);
    Flux<TransactionDto> filterTransactionsByLastMonth(LocalDateTime currentDate);
    Flux<TransactionDto> filterTransactionsByLastThreeMonths(LocalDateTime currentDate);
    Flux<TransactionDto> filterTransactionsByCustomPeriod(LocalDateTime startDate, LocalDateTime endDate);
}
