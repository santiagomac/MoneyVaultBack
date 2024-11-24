package co.com.bancolombia.model.transaction.gateways;

import co.com.bancolombia.model.transaction.TransactionDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionGateway {

    Mono<TransactionDto> create(TransactionDto transactionDto);
    Mono<TransactionDto> getById(Long id);
    Flux<TransactionDto> getAllExpenses();
    Mono<Void> update(TransactionDto transactionDto);
    Mono<Void> delete(Long id);
    Flux<TransactionDto> getExpensesByBudgetId(Long id);
}
