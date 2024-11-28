package co.com.bancolombia.api.filter;

import co.com.bancolombia.model.transaction.TransactionDto;
import co.com.bancolombia.usecase.filter.FilterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class HandlerFilter {

    private final FilterUseCase filterUseCase;

    Mono<ServerResponse> filterPastWeek(ServerRequest request) {
        String period = request.queryParam("period").orElse(null);
        String startDate = request.queryParam("startDate").orElse(null);
        String endDate = request.queryParam("endDate").orElse(null);

        return ServerResponse.ok().body(
                this.filterUseCase.filterTransactions(period, startDate, endDate),
                TransactionDto.class);
    }
}
