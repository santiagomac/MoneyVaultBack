package co.com.bancolombia.api.expense;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(HandlerV1 handlerV1) {
        return RouterFunctions
                .route()
                .path("/api/v1", builder -> builder
                        .GET("/expense/{id}", handlerV1::getExpense)
                        .POST("/expense/createExpense", handlerV1::createExpense)
                        .GET("/expense", handlerV1::getExpenses)
                        .PUT("/expense/{id}", handlerV1::updateExpense)
                        .DELETE("/expense/{id}", handlerV1::deleteExpense)
                ).build();
    }
}
