package co.com.bancolombia.api.budget;

import co.com.bancolombia.model.budget.BudgetDto;
import co.com.bancolombia.usecase.budget.BudgetUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class HandlerBudget {

    private final BudgetUseCase budgetUseCase;

    public Mono<ServerResponse> createBudget(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(BudgetDto.class)
                .flatMap(this.budgetUseCase::createBudget)
                .flatMap(expense -> ServerResponse.ok().bodyValue(expense))
                .onErrorResume(ex -> ServerResponse.badRequest().bodyValue(ex.getMessage()));
    }

    public Mono<ServerResponse> getAllBudgets(ServerRequest serverRequest) {
        return ServerResponse.ok().body(this.budgetUseCase.getAllBudgets(), BudgetDto.class);
    }

    public Mono<ServerResponse> getBudgetById(ServerRequest serverRequest) {
        Long id = Long.parseLong(serverRequest.pathVariable("id"));
        return this.budgetUseCase.getBudgetById(id)
                .flatMap(budget -> ServerResponse.ok().bodyValue(budget))
                .onErrorResume(ex -> ServerResponse.badRequest().bodyValue(ex.getMessage()));
    }

    public Mono<ServerResponse> updateBudget(ServerRequest serverRequest) {
        Long id = Long.parseLong(serverRequest.pathVariable("id"));
        return serverRequest.bodyToMono(BudgetDto.class)
                .flatMap(budget -> this.budgetUseCase.updateBudget(id, budget))
                .then(Mono.defer(() -> ServerResponse.noContent().build()))
                .onErrorResume(ex -> ServerResponse.badRequest().bodyValue(ex.getMessage()));
    }

    public Mono<ServerResponse> deleteBudget(ServerRequest serverRequest) {
        Long id = Long.parseLong(serverRequest.pathVariable("id"));
        return this.budgetUseCase.deleteBudget(id)
                .then(Mono.defer(() -> ServerResponse.noContent().build()))
                .onErrorResume(ex -> ServerResponse.badRequest().bodyValue(ex.getMessage()));
    }
}