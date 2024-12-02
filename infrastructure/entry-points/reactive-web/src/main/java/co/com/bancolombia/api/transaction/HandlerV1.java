package co.com.bancolombia.api.transaction;

import co.com.bancolombia.model.transaction.TransactionDto;
import co.com.bancolombia.usecase.expense.ExpenseUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class HandlerV1 {

    private final ExpenseUseCase expenseUseCase;
//private  final UseCase2 useCase2;

    public Mono<ServerResponse> createTransaction(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(TransactionDto.class)
                .flatMap(this.expenseUseCase::createExpense)
                .flatMap(expense -> ServerResponse.ok().bodyValue(expense))
                .onErrorResume(ex -> ServerResponse.badRequest().bodyValue(ex.getMessage()));
    }

    public Mono<ServerResponse> getExpenses(ServerRequest serverRequest) {
        Long userId = Long.parseLong(serverRequest.pathVariable("userId"));
        return ServerResponse.ok().body(this.expenseUseCase.getAllExpenses(userId), TransactionDto.class);
    }

    public Mono<ServerResponse> getExpense(ServerRequest serverRequest) {
        try {
            Long id = Long.parseLong(serverRequest.pathVariable("id"));
            Long userId = Long.parseLong(serverRequest.pathVariable("userId"));
            log.info("Getting expense with id: {}", id);
            return this.expenseUseCase.getExpense(id, userId)
                    .flatMap(expense -> ServerResponse.ok().bodyValue(expense));
        } catch (NumberFormatException ex) {
            return ServerResponse.badRequest().bodyValue("Invalid ID: must be a number");
        }
    }

    public Mono<ServerResponse> updateExpense(ServerRequest serverRequest) {
        // useCase.logic();
        Long id = Long.parseLong(serverRequest.pathVariable("id"));
        return serverRequest.bodyToMono(TransactionDto.class)
                .flatMap(expense -> expenseUseCase.updateExpense(id, expense))
                .then(Mono.defer(() -> ServerResponse.noContent().build()))
                .onErrorResume(ex -> ServerResponse.badRequest().bodyValue(ex.getMessage()));
    }

    public Mono<ServerResponse> deleteExpense(ServerRequest serverRequest) {
        Long id = Long.parseLong(serverRequest.pathVariable("id"));
        return this.expenseUseCase.deleteExpense(id)
                .then(Mono.defer(() -> ServerResponse.noContent().build()))
                .onErrorResume(ex -> ServerResponse.badRequest().bodyValue(ex.getMessage()));
    }
}
