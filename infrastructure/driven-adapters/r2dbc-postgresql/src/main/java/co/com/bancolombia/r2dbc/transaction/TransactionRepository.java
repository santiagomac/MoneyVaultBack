package co.com.bancolombia.r2dbc.transaction;

import co.com.bancolombia.model.transaction.TransactionDto;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

public interface TransactionRepository extends ReactiveCrudRepository<TransactionEntity, Long>, ReactiveQueryByExampleExecutor<TransactionEntity> {

    Flux<TransactionDto> findByBudgetId(Long budgetId);

    @Query("SELECT * FROM transactions WHERE date >= :currentDate::DATE - INTERVAL '7 days'")
    Flux<TransactionEntity> filterTransactionsByLastWeek(@Param("currentDate") LocalDateTime currentDate);

    @Query("SELECT * FROM transactions WHERE date >= :currentDate::DATE - INTERVAL '30 days'")
    Flux<TransactionEntity> filterTransactionsByLastMonth(@Param("currentDate") LocalDateTime currentDate);

    @Query("SELECT * FROM transactions WHERE date >= :currentDate::DATE - INTERVAL '90 days'")
    Flux<TransactionEntity> filterTransactionsByLastThreeMonths(@Param("currentDate") LocalDateTime currentDate);

    @Query("SELECT * FROM transactions WHERE date BETWEEN :startDate AND :endDate")
    Flux<TransactionEntity> filterTransactionsByCustomPeriod(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
